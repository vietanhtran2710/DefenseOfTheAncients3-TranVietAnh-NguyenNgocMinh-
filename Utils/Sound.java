package Utils;

import org.lwjgl.openal.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.libc.LibCStdlib.*;

public class Sound {
    private long   device;

    private int[] attributes = {0};
    private long  context;

    private ShortBuffer rawAudioBuffer;

    private int channels;
    private int sampleRate;

    private String filePath;

    private int bufferPointer;
    private int sourcePointer;

    private boolean isPlay;

    public Sound(String filePath) {
        this.filePath = filePath;
    }

    public void init() {
        this.isPlay = false;

        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        this.device = alcOpenDevice(defaultDeviceName);

        this.context = alcCreateContext(device, attributes);
        alcMakeContextCurrent(context);

        ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

        MemoryStack stack = stackPush();
        //Allocate space to store return information from the function
        IntBuffer channelsBuffer = stack.mallocInt(1);
        IntBuffer sampleRateBuffer = stack.mallocInt(1);

        this.rawAudioBuffer = stb_vorbis_decode_filename(filePath, channelsBuffer, sampleRateBuffer);

        //Retreive the extra information that was stored in the buffers by the function
        this.channels = channelsBuffer.get(0);
        this.sampleRate = sampleRateBuffer.get(0);

    }

    public void play() {
        init();
        //Find the correct OpenAL format
        int format = -1;
        if (channels == 1) {
            format = AL_FORMAT_MONO16;
        } else if (channels == 2) {
            format = AL_FORMAT_STEREO16;
        }

        //Request space for the buffer
        this.bufferPointer = alGenBuffers();

        //Send the data to OpenAL
        alBufferData(bufferPointer, format, rawAudioBuffer, sampleRate);

        //Free the memory allocated by STB
        free(rawAudioBuffer);

        //Request a source
        this.sourcePointer = alGenSources();

        //Assign the sound we just loaded to the source
        alSourcei(sourcePointer, AL_BUFFER, bufferPointer);

        //Play the sound
        alSourcePlay(sourcePointer);

        this.isPlay = true;
    }

    public void delete() {
        if (this.isPlay) {
            alDeleteSources(sourcePointer);
            alDeleteBuffers(bufferPointer);
            alcDestroyContext(context);
            alcCloseDevice(device);
            this.isPlay = false;
        }
    }

}

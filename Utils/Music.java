package Utils;

public class Music {
    private Sound sound;
    private boolean isPlay;
    private double time_1;
    private double time_2;

    public Music(String filePath) {
        this.sound = new Sound(filePath);
        this.time_1 = Timer.getTime();
        this.time_2 = 0.0;
        this.isPlay = false;
    }

    public void playFor (double second, boolean isMuted) {
        if (this.time_2 - this.time_1 < second && !isPlay && !isMuted) {
            this.sound.play();
            this.isPlay = true;
        }

        if ((this.time_2 - this.time_1 >= second || isMuted) && isPlay) {
            this.sound.delete();
            this.isPlay = false;
        }
        else this.time_2 = Timer.getTime();
    }

    public void playLoop (double musicLength, boolean isMuted) {
        if (this.time_2 - this.time_1 < musicLength && !isPlay && !isMuted) {
            this.sound.play();
            this.isPlay = true;
        }

        if ((this.time_2 - this.time_1 >= musicLength || isMuted) && isPlay) {
            this.sound.delete();
            this.isPlay = false;
            this.time_1 = Timer.getTime();
        }
        else this.time_2 = Timer.getTime();
    }

    public void delete() {
        this.sound.delete();
    }
}

import greenfoot.*; 
import java.util.Map;
import java.util.HashMap;
public class SoundManager  
{
    private Map<String,GreenfootSound> soundMap = new HashMap<String,GreenfootSound>(25);
    private static final int defVolume = 50;
    public static double volMultiplier = .7;
    public SoundManager()
    {

        String st = "sounds\\step\\";
        String pl = "sounds\\place\\";
        String hi = "sounds\\hit\\";
        String end = ".wav";
        for(int i=1;i<=3;i++)
        {
           soundMap.put("grassstep"+i,new GreenfootSound(st+"grass"+i+end));
           soundMap.put("stonestep"+i,new GreenfootSound(st+"stone"+i+end));
           soundMap.put("woodstep"+i,new GreenfootSound(st+"wood"+i+end));
           soundMap.put("dirtstep"+i,new GreenfootSound(st+"dirt"+i+end));
        }     
        for(int i=1;i<=3;i++)
        {
           soundMap.put("grasshit"+i,new GreenfootSound(hi+"grass"+i+end));
           soundMap.put("stonehit"+i,new GreenfootSound(hi+"stone"+i+end));
           soundMap.put("woodhit"+i,new GreenfootSound(hi+"wood"+i+end));
           soundMap.put("dirthit"+i,new GreenfootSound(hi+"dirt"+i+end));
        }   
        soundMap.put("grassplace",new GreenfootSound(pl+"grass"+end));
        soundMap.put("stoneplace",new GreenfootSound(pl+"stone"+end));
        soundMap.put("woodplace",new GreenfootSound(pl+"wood"+end));
        soundMap.put("sandplace",new GreenfootSound(pl+"sand"+end));
        soundMap.put("dirtplace",new GreenfootSound(pl+"dirt"+end));
        soundMap.put("click",new GreenfootSound("sounds\\click"+end));
        soundMap.put("mchammer",new GreenfootSound("sounds\\stop"+end));
    }
    public void play(String sound,int volume)
    {

        GreenfootSound s = soundMap.get(sound);
        if(s.isPlaying())
        {
            s = getCopy(s);
        }
        s.setVolume((int)Math.round(volume*volMultiplier));
        s.play();
    }
    public void play(String sound)
    {

        GreenfootSound s = soundMap.get(sound);
        if(s.isPlaying())
        {
            s = getCopy(s);
        }
        s.setVolume((int)Math.round(defVolume*volMultiplier));
        s.play();
    }
    public void playRandStep(String sound)
    {

        int rand = Greenfoot.getRandomNumber(3)+1;
        play(sound+"step"+rand,20);
    }
    public void playRandHit(String sound)
    {

        int rand = Greenfoot.getRandomNumber(3)+1;
        play(sound+"hit"+rand,70);
    }
    private GreenfootSound getCopy(GreenfootSound s)
    {

        String string = s.toString();
        string = string.substring(string.indexOf("e: ")+3,string.indexOf(".wav")+4);
        return new GreenfootSound(string);
    }
}

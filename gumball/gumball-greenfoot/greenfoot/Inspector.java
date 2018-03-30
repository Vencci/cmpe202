import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Inspector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inspector extends Alien
{
    private ArrayList<Picker> pickers = new ArrayList<>();
    Message m = new Message();
    Actor haveCoin;
    private void setMessage(String msg) {
        int mouseX, mouseY;
        MouseInfo mouse = Greenfoot.getMouseInfo();
        mouseX = mouse.getX();
        mouseY = mouse.getY();
        World world = getWorld();
        if (m.getWorld() != null) {
            world.removeObject(m);
        }
        world.addObject(m, mouseX, mouseY);
        m.setText(msg);
    }
    
    public void addPicker(Picker obj) {
        pickers.add(obj);
    }
    
    public void removePicker(Picker obj) {
        pickers.remove(obj);
    }
    
    public void inspect(Coin coin) {
        System.out.println("Coin:" + coin.getClass());
        setMessage(coin.getClass().getName());
        Actor randomPicker;
        Actor greenPicker;
        randomPicker = getOneObjectAtOffset(+643-533, +104-291, Picker.class);
        greenPicker = getOneObjectAtOffset(+686-533, +454-291, Picker.class);
        pickers.add((Picker)randomPicker);
        pickers.add((Picker)greenPicker);
        int whichPicker = Greenfoot.getRandomNumber(pickers.size());
        Picker pickerChosen = pickers.get(whichPicker);
        System.out.println("Picker:" + pickerChosen.getClass());
        if (coin.getClass() == Quarter.class) {
            if (pickerChosen.getClass() == RandomPicker.class) {
                ((RandomPicker)pickerChosen).pick();
            } else {
                ((GreenPicker)pickerChosen).pick();
            }
        }
    }
    /**
     * Act - do whatever the Inspector wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mousePressed(this)) {
            if (haveCoin != null){
                if (haveCoin.getClass() == Quarter.class) {
                    inspect((Coin)haveCoin);
                    Greenfoot.stop();
                }
            }
        }
        Actor coin;
        coin = getOneObjectAtOffset(367-533+10, 237-291+10, Coin.class);
        if (coin != null) {
            if(haveCoin == null) {
                haveCoin = coin;
            }
        }
    }    
}

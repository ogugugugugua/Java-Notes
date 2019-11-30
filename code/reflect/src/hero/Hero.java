package hero;

public class Hero {
    public int damage;
    public float hp;
    public String name;

    public Hero() {
        this.name = "default";
    }

    public void attackHero(Hero h){
        if(!h.isDead()&&!this.isDead()){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            h.hp -= this.damage;
            System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
        }
        if(h.isDead()){
            System.out.println("Hero "+h.name+" is dead.");
        }
    }
    public Hero(int damage, float hp, String name){
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public String getName(){
        return this.name;
    }
    public boolean isDead(){
        return hp<0;
    }

    public String toString(){
        return "["+this.name+"]";
    }
}
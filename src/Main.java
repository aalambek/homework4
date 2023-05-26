import java.util.Random;

public class Main {

    public static int bossHealth = 700;

    public static int bossDamage = 50;

    public static String bossDefence;

    public static int[] heroHealth = {270, 260, 250, 300};

    public static int[] heroDamage = {10, 15, 20, 0};
    public static String[] heroesAttackTupe = {"Physical", "Magical", "kinetic", "Medic"};
    public static int medicUnit = 30;
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void medicHeals(){
        for (int i = 0; i < heroHealth.length; i++) {
            if (heroHealth[i] >0 && heroHealth[i]<100 && heroHealth[3] > 0 && heroesAttackTupe[i] != heroesAttackTupe[3]){
                heroHealth[i]= heroHealth[i] + medicUnit;
                System.out.println("Medic healed on: "+medicUnit+ heroesAttackTupe[i]);
                break;
            }
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackTupe.length);//0,1,2
        bossDefence = heroesAttackTupe[randomIndex];
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHits();
        medicHeals();
        printStatistics();
    }

    public static void bossHits() {
        for (int i = 0; i < heroHealth.length; i++) {
            if (heroHealth[i] > 0) {
                if (heroHealth[i] - bossDamage < 0) {
                    heroHealth[i] = 0;
                } else {
                    heroHealth[i] = heroHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHits() {
        for (int i = 0; i < heroDamage.length; i++) {
            if (heroHealth[i] > 0 && bossHealth > 0) {
                int damage = heroDamage[i];
                if (heroesAttackTupe[i] == bossDefence){
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2;//2,3,4,5,6,7,8,9,10
                    damage = heroDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroDead = true;
        for (int i = 0; i < heroHealth.length; i++) {
            if (heroHealth[i] > 0) {
                allHeroDead = false;
                break;
            }
        }
        if (allHeroDead) {
            System.out.println("boss won!!!");
        }
        return allHeroDead;
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + "_ _ _ _ _ _ _ _ _ _");
        System.out.println("Boss health: " + bossHealth + "damage: " +
                bossDamage + "defence: " +
                (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroHealth.length; i++) {
            System.out.println(heroesAttackTupe[i] +
                    "health: " + heroHealth[i] + " damage: " + heroDamage[i]);
        }
    }
}
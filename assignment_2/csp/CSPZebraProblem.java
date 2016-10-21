import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class CSPZebraProblem extends CSP {

    private enum Var {
        BLUE, GREEN, IVORY, RED, YELLOW,
        COFFEE, MILK, ORANGE_JUICE, TEA, WATER,
        ENGLISHMAN, JAPANESE, NORWEGIAN, SPANIARD, UKRAINIAN,
        DOG, FOX, HORSE, SNAILS, ZEBRA,
        CHESTERFIELD, KOOLS, LUCKY_STRIKE, OLD_GOLD, PARLIAMENT
    }

    private static final Set<Var> colours = new HashSet<>(
            Arrays.asList(
                    new Var[]{Var.BLUE, Var.GREEN, Var.IVORY, Var.RED, Var.YELLOW}
            )
    );

    private static final Set<Var> pets = new HashSet<>(
            Arrays.asList(
                    new Var[]{Var.FOX, Var.HORSE, Var.DOG, Var.SNAILS, Var.ZEBRA}
            )
    );

    private static final Set<Var> drinks = new HashSet<>(
            Arrays.asList(
                    new Var[]{Var.COFFEE, Var.MILK, Var.WATER, Var.TEA, Var.ORANGE_JUICE}
            )
    );

    private static final Set<Var> nationalities = new HashSet<>(
            Arrays.asList(
                    new Var[]{Var.UKRAINIAN, Var.NORWEGIAN, Var.ENGLISHMAN, Var.JAPANESE, Var.SPANIARD}
            )
    );

    private static final Set<Var> cigarettes = new HashSet<>(
            Arrays.asList(
                    new Var[]{Var.CHESTERFIELD, Var.KOOLS, Var.LUCKY_STRIKE, Var.PARLIAMENT, Var.OLD_GOLD}
            )
    );

    public boolean isGood(Object Xi, Object Yi, Object xi, Object yi) {
        Var X = (Var) Xi;
        Var Y = (Var) Yi;
        Integer x = (Integer) xi;
        Integer y = (Integer) yi;

        //if X is not even mentioned in by the constraints, just return true
        //as nothing can be violated
        if (!C.containsKey(X))
            return true;

        //check to see if there is an arc between X and Y
        //if there isn't an arc, then no constraint, i.e. it is good
        if (!C.get(X).contains(Y))
            return true;

        // Check uniqueness constraints
        if (colours.contains(X) && colours.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
        if (drinks.contains(X) && drinks.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
        if (pets.contains(X) && pets.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
        if (nationalities.contains(X) && nationalities.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
        if (cigarettes.contains(X) && cigarettes.contains(Y) && !X.equals(Y) && x.equals(y)) return false;

        //1.The Englishman lives in the red house.
        if(X.equals(Var.ENGLISHMAN) && Y.equals(Var.RED) && !x.equals(y)) return false;

        //2.The Spaniard owns a dog.
        if(X.equals(Var.SPANIARD) && Y.equals(Var.DOG) && !x.equals(y)) return false;

        //3.Coffee is drunk in the green house.
        if(X.equals(Var.COFFEE) && Y.equals(Var.GREEN) && !x.equals(y)) return false;

        //4.The Ukrainian drinks tea.
        if(X.equals(Var.UKRAINIAN) && Y.equals(Var.TEA) && !x.equals(y)) return false;

        //5.The green house is directly to the right of the ivory house.
        if(X.equals(Var.GREEN) && Y.equals(Var.IVORY) && x-y != 1) return false;

        //6.The Old-Gold smoker owns snails.
        if(X.equals(Var.OLD_GOLD) && Y.equals(Var.SNAILS) && !x.equals(y)) return false;

        //7.Kools are being smoked in the yellow house.
        if(X.equals(Var.KOOLS) && Y.equals(Var.YELLOW) && !x.equals(y)) return false;

        //8.Milk is drunk in the middle house.
        //9.The Norwegian lives in the first house on the left.

        //10.The Chesterfield smoker lives next to the fox owner.
        if(X.equals(Var.CHESTERFIELD) && Y.equals(Var.FOX) && Math.abs(x-y) != 1) return false;

        //11.Kools are smoked in the house next to the house where the horse is kept.
        if(X.equals(Var.KOOLS) && Y.equals(Var.HORSE) && Math.abs(x-y) != 1) return false;

        //12.The Lucky-Strike smoker drinks orange juice.
        if(X.equals(Var.LUCKY_STRIKE) && Y.equals(Var.ORANGE_JUICE) && !x.equals(y)) return false;

        //13.The Japanese smokes Parliament.
        if(X.equals(Var.JAPANESE) && Y.equals(Var.PARLIAMENT) && !x.equals(y)) return false;

        //14.The Norwegian lives next to the blue house.
        if(X.equals(Var.NORWEGIAN) && Y.equals(Var.BLUE) && Math.abs(x-y) != 1) return false;

        return true;
    }

    public static void main(String[] args) {
        CSPZebraProblem csp = new CSPZebraProblem();

        Integer[] houses = {1,2,3,4,5};

        // Add the house domain to each of the variables
        for(Var colour : colours)
            csp.addDomain(colour, houses);
        for(Var pet : pets)
            csp.addDomain(pet, houses);
        for(Var nation : nationalities)
            csp.addDomain(nation, houses);
        for(Var cig : cigarettes)
            csp.addDomain(cig, houses);
        for(Var drink : drinks)
            csp.addDomain(drink, houses);

        // Encode the uniqueness constraints
        for(Var colour1 : colours) {
            for(Var colour2 : colours) {
                csp.addBidirectionalArc(colour1, colour2);
            }
        }
        for(Var drink1 : drinks) {
            for(Var drink2 : drinks) {
                csp.addBidirectionalArc(drink1, drink2);
            }
        }
        for(Var pet1 : pets) {
            for(Var pet2 : pets) {
                csp.addBidirectionalArc(pet1, pet2);
            }
        }
        for(Var cig1 : cigarettes) {
            for(Var cig2 : cigarettes) {
                csp.addBidirectionalArc(cig1, cig2);
            }
        }
        for(Var nation1 : nationalities) {
            for(Var nation2 : nationalities) {
                csp.addBidirectionalArc(nation1, nation2);
            }
        }

        // Add an arc between all variables with constraints between them
        //1.The Englishman lives in the red house.
        csp.addBidirectionalArc(Var.ENGLISHMAN, Var.RED);
        //2.The Spaniard owns a dog.
        csp.addBidirectionalArc(Var.SPANIARD, Var.DOG);
        //3.Coffee is drunk in the green house.
        csp.addBidirectionalArc(Var.COFFEE, Var.GREEN);
        //4.The Ukrainian drinks tea.
        csp.addBidirectionalArc(Var.UKRAINIAN, Var.TEA);
        //5.The green house is directly to the right of the ivory house.
        // No need to add another arc, there is already uniqueness constraint on colours
        //6.The Old-Gold smoker owns snails.
        csp.addBidirectionalArc(Var.SNAILS, Var.OLD_GOLD);
        //7.Kools are being smoked in the yellow house.
        csp.addBidirectionalArc(Var.KOOLS, Var.YELLOW);
        //8.Milk is drunk in the middle house.
        csp.addDomain(Var.MILK, new Integer[]{3});
        //9.The Norwegian lives in the first house on the left.
        csp.addDomain(Var.NORWEGIAN, new Integer[]{1});
        //10.The Chesterfield smoker lives next to the fox owner.
        csp.addBidirectionalArc(Var.CHESTERFIELD, Var.FOX);
        //11.Kools are smoked in the house next to the house where the horse is kept.
        csp.addBidirectionalArc(Var.KOOLS, Var.HORSE);
        //12.The Lucky-Strike smoker drinks orange juice.
        csp.addBidirectionalArc(Var.LUCKY_STRIKE, Var.ORANGE_JUICE);
        //13.The Japanese smokes Parliament.
        csp.addBidirectionalArc(Var.JAPANESE, Var.PARLIAMENT);
        //14.The Norwegian lives next to the blue house.
        csp.addBidirectionalArc(Var.NORWEGIAN, Var.BLUE);

        Search search = new Search(csp);
        System.out.println(search.BacktrackingSearch());
    }
}

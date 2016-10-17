
import java.util.ArrayList;
import java.util.Random;

/**
 * <code>Environment</code> class is where the game mechanics happen. <code>Environment</code> creates all
 * <code>Entity</code>s. Stepping all <code>Entity</code>s are done in this class. 
 * According to <code>Entities</code> states, <code>Environment</code> sets fields of <code>Entity</code>s.
 * <code>Environment</code> also decorates <code>Terrorist</code>s and adjust explosions.
 * In case of explosion, <code>Environment</code> replaces the dead <code>Entity</code>s with new ones.
 * 
 * @see Entity
 */
public final class Environment {

    public final EntityFactory[] factories;

    public ArrayList<Entity> entities;
    public int caughtByAgents = 0, caughtBySoldiers = 0;
    public int deadSoldier = 0, deadAgent = 0, deadCitizen = 0, deadTerrorist = 0;

    /**
     * Initializes a new environment including stationary 3 <code>Camp</code>, 3
     * <code>SupplyStorage</code> and non-stationary 2 <code>Untouchable</code>
     * instances.
     */
    public Environment() {
        entities = new ArrayList<>();
        factories = new EntityFactory[4];
        factories[0] = new AgentFactory();
        factories[1] = new CitizenFactory();
        factories[2] = new SoldierFactory();
        factories[3] = new TerroristFactory();

        // CRATE UNTOUCHABLES, SUPPLY_STORAGES AND CAMPS
        entities.add(new Camp(0));
        entities.add(new Camp(1));
        entities.add(new Camp(2));

        entities.add(new SupplyStorage(0));
        entities.add(new SupplyStorage(1));
        entities.add(new SupplyStorage(2));

        entities.add(new Untouchable(0));//TAYIP
        entities.add(new Untouchable(1));//AHMET
        completeEntities();
    }

    /**
     * Creates an <code>Entity</code> instance for the given name.
     *
     * @param entityName Name of the subclasses of the Entity class."Citizen",
     * "Terrorist", "Agent" and "Soldier" are valid parameters.
     * @return Returns an <code>Entity</code> instance for the given
     * <code>entityName</code>.
     */
    public Entity createEntity(String entityName) {
        // TODO implement here
        switch (entityName) {
            case "Citizen":
                return this.factories[1].createEntity();
            case "Terrorist":
                return this.factories[3].createEntity();
            case "Agent":
                return this.factories[0].createEntity();
            case "Soldier":
                return this.factories[2].createEntity();
            default:
                break;
        }
        return null;
    }

    /**
     * Checks for the missing Entities to create new ones. If environment has
     * less instances of one of <code>Citizen</code>, <code>Terrorist</code>,
     * <code>Agent</code> and <code>Soldier</code> classes than it should be,
     * missing instances are created. Instance counts are stated in the
     * properties file.
     *
     * @see AllProperties
     */
    public void completeEntities() {
        int count = 0;
        while (count < AllProperties.CITIZENS) {
            this.entities.add(createEntity("Citizen"));
            count++;
        }
        count = 0 ;
        while (count < AllProperties.SOLDIERS) {
            this.entities.add(createEntity("Soldier"));
            count++;
        }
        count = 0;
        while (count < AllProperties.AGENTS) {
            this.entities.add(createEntity("Agent"));
            count++;
        }
        count = 0;
        while (count < AllProperties.TERRORISTS) {
            this.entities.add(createEntity("Terrorist"));
            count++;
        }
    }

    /**
     * @param e <code>Entity</code> instance whose new strategy is generated.
     * @return Returns a new and different<code>StepStrategy</code> instance
     * according to the <code>e</code>'s current strategy.
     * @see StepStrategy
     * @see Entity
     */
    public StepStrategy generateStrategy(Entity e) {
        // TODO implement here
        boolean isAgentOrSoldier = false;
        Random rand = new Random();
        int strategy = 0;
        int numOfTurns = rand.nextInt(30) + 30;//30 la 60 arasi random turn miktari

        if (e instanceof Citizen) {
            strategy = rand.nextInt(3);
        } else if (e instanceof Equipped) {
            strategy = rand.nextInt(3);
        } else if (e instanceof Untouchable) {
            strategy = rand.nextInt(2);
        } else if ((e instanceof RecentlyDeceivedTerrorist)
                || (e instanceof BrainWashed)) {
            strategy = rand.nextInt(4);
        } else if (e instanceof Agent) {
            strategy = rand.nextInt(2);
            isAgentOrSoldier = true;
        } else if (e instanceof Soldier) {
            strategy = rand.nextInt(3);
            isAgentOrSoldier = true;
        } else if (e instanceof Exploded) {
            return new StandStill(numOfTurns);
        }

        if (!isAgentOrSoldier) {
            switch (strategy) {
                case 0:
                    return new StandStill(numOfTurns);
                case 1:
                    return new MoveLinear(numOfTurns);
                case 2:
                    return new MoveRandom(numOfTurns);
                case 3:
                    return new GoToPosition(numOfTurns, randomCampOrStorage(e));
            }
        } else {
            switch (strategy) {
                case 0:
                    return new StandStill(numOfTurns);
                case 1:
                    return new MoveLinear(numOfTurns);
                case 2:
                    return new Patrol(numOfTurns, e.position);
            }
        }
        return new StandStill(3);
    }

    
    /**
     * Finds a random <code>Camp</code> or <code>SupplyStorage</code>
     * for the given <code>Entity</code>.
     * 
     * @param e <code>Entity</code> 
     * @return If <code>e</code> is instance of <code>BrainWashed</code> returns a random <code>SupplyStorage</code>; random
     * <code>Camp</code> otherwise.
     * 
     * @see Camp
     * @see SupplyStorage
     */
    private Position randomCampOrStorage(Entity e) {
        Random rand = new Random();
        Position p = new Position();
        if (e instanceof RecentlyDeceivedTerrorist) {// entities in 1 2 3 u CAMP
            Entity camp = entities.get(rand.nextInt(3));
            p = camp.position;
        } else if (e instanceof BrainWashed) { // entities in 1 2 3 u SUPPLY STORAGE
            Entity camp = entities.get(rand.nextInt(3) + 3);
            p = camp.position;
        }
        return p;
    }

    /**
     * Detects whether a <code>Entity</code> is in a <code>Camp</code>.
     * 
     * @param e <code>Entity</code>
     * @return <code>true</code> if <code>e</code> is in a <code>Camp</code>.<code>false</code> otherwise
     */
    private boolean isInCamp(Entity e) {
        for (int i = 0; i < 3; i++) {

            if (e.isInTheRange(entities.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Detects whether a <code>Entity</code> is in a <code>SupplyStorage</code>.
     * 
     * @param e <code>Entity</code>
     * @return <code>true</code> if <code>e</code> is in a <code>SupplyStorage</code>.<code>false</code> otherwise.
     */
    private boolean isInStorage(Entity e) {
        for (int i = 3; i < 6; i++) {
            if (e.isInTheRange(entities.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Steps all <code>Entity</code>s. Sets the strategy field of an <code>Entity</code> if it is finished.
     * Detects whether a <code>Terrorist</code> will be decorated or not. 
     * Controls explosions of <code>Equipped</code> instances. Substitutes the dead <code>Entity</code>s with new ones.
     * 
     * @param deltaTime time to step.
     */
    public synchronized void stepAll(double deltaTime) {
        for (int i = 6; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.step(deltaTime);
            if (e.strategy.isFinished()) {
                e.setStrategy(generateStrategy(e));
            }

            if (e instanceof Terrorist) {
                for (int j = AllProperties.CITIZENS + 5; j < entities.size(); j++) {
                    if ((entities.get(j) instanceof Soldier) && e.isInTheRange(entities.get(j))) {
                        caughtBySoldiers++;
                        entities.set(i, createEntity("Terrorist"));
                    } else if ((entities.get(j) instanceof Agent) && e.isInTheRange(entities.get(j))) {
                        caughtByAgents++;
                        entities.set(i, createEntity("Terrorist"));
                    }
                }
                if (e instanceof RecentlyDeceivedTerrorist && isInCamp(e)) {
                    entities.set(i, TerroristDecoratorFactory.createDecorator((Terrorist) e, TerroristDecoratorFactory.BRAIN_WASHED));
                } else if (e instanceof BrainWashed && isInStorage(e)) {
                    entities.set(i, TerroristDecoratorFactory.createDecorator((Terrorist) e, TerroristDecoratorFactory.EQUIPPED));
                }
                else if (e instanceof Equipped) {
                    if (e.strategy instanceof StandStill) {
                        if (((StandStill) (((Equipped) e).strategy)).getProbability() == 0) { // 0.1  prob BURDDA PATLIYOR
                            entities.set(i, TerroristDecoratorFactory.createDecorator((Terrorist) e, TerroristDecoratorFactory.EXPLODED));
                            stopProspectiveDeads(e);
                        }
                    }
                }else if(e instanceof Exploded){
                    if (((Exploded) e).isCompleted()){
                        killProspectiveDeads(e);
                        deadTerrorist++;
                        entities.set(i, createEntity("Terrorist"));
                    }
                }
                
            }
        }
    }

    private void stopProspectiveDeads(Entity e) {
        for (int j = 8; j < entities.size(); j++) {
            Entity prospectiveDead = entities.get(j);
            if (prospectiveDead.isInTheRange(e)) {
                if (prospectiveDead != e) {
                    prospectiveDead.strategy = new StandStill(100);
                }
            }
        }
    }
    private void killProspectiveDeads(Entity e) {
        for (int j = 8; j < entities.size(); j++) {
            Entity prospectiveDead = entities.get(j);
            if (prospectiveDead.isInTheRange(e) && prospectiveDead != e) {
                switch(prospectiveDead.name){
                    case "Citizen":
                        deadCitizen++;
                        entities.set(j, createEntity("Citizen"));
                        break;
                    case "Agent":
                        deadAgent++;
                        entities.set(j, createEntity("Agent"));
                        break;
                    case "Soldier":
                        deadSoldier++;
                        entities.set(j, createEntity("Soldier"));
                        break;
                    case "Terrorist":
                        deadTerrorist++;
                        entities.set(j, createEntity("Terrorist"));
                        break;
                    default:
                        break;
                    
                }
            }
        }
    }
}

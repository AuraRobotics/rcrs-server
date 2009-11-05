package rescuecore2.messages.control;

import rescuecore2.messages.Control;
import rescuecore2.messages.AbstractMessage;
import rescuecore2.messages.components.IntComponent;
import rescuecore2.messages.components.ChangeSetComponent;
import rescuecore2.worldmodel.ChangeSet;

import java.io.InputStream;
import java.io.IOException;

/**
   A broadcast update from the kernel.
 */
public class Update extends AbstractMessage implements Control {
    private IntComponent id;
    private IntComponent time;
    private ChangeSetComponent changes;

    /**
       An Update message that populates its data from a stream.
       @param in The InputStream to read.
       @throws IOException If there is a problem reading the stream.
     */
    public Update(InputStream in) throws IOException {
        this();
        read(in);
    }

    /**
       A populated Update message.
       @param id The id of the simulator or viewer receiving the update.
       @param time The timestep of the simulation.
       @param changes The changeset.
     */
    public Update(int id, int time, ChangeSet changes) {
        this();
        this.id.setValue(id);
        this.time.setValue(time);
        this.changes.setChangeSet(changes);
    }

    private Update() {
        super(ControlMessageURN.UPDATE);
        id = new IntComponent("ID");
        time = new IntComponent("Time");
        changes = new ChangeSetComponent("Changes");
        addMessageComponent(id);
        addMessageComponent(time);
        addMessageComponent(changes);
    }

    /**
       Get the id of the component that this message is addressed to.
       @return The ID of the target component.
     */
    public int getTargetID() {
        return id.getValue();
    }

    /**
       Get the time of the simulation.
       @return The simulation time.
     */
    public int getTime() {
        return time.getValue();
    }

    /**
       Get the list of changes.
       @return The changes.
     */
    public ChangeSet getChangeSet() {
        return changes.getChangeSet();
    }
}
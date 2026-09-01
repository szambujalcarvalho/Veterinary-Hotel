package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.DuplicateTreeKeyExceptionCore;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("habitatKey", Prompt.habitatKey());
    addStringField("treeKey", Prompt.treeKey());
    addStringField("treeName", Prompt.treeName());
    addIntegerField("treeAge", Prompt.treeAge());
    addIntegerField("treeDifficulty", Prompt.treeDifficulty());
    addStringField("treeType", Prompt.treeType());        
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
    _display.popup(_receiver.plantTree(stringField("treeKey"), stringField("treeName"), stringField("treeType"),integerField("treeAge"), integerField("treeDifficulty"), stringField("habitatKey")));
    }catch(DuplicateTreeKeyExceptionCore e){
      throw new DuplicateTreeKeyException(e.getKey());
    }catch(UnknownHabitatKeyExceptionCore e){
      throw new UnknownHabitatKeyException(e.getKey());
    }
  }
}
package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.core.exception.UnknownSpeciesKeyExceptionCore;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.animal.Prompt;
//FIXME add more imports if needed

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
    addStringField("speciesKey", Prompt.speciesKey());
    addStringField("habitatInfluence", hva.app.habitat.Prompt.habitatInfluence());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
    _receiver.alteraAdequacao(stringField("habitatKey"), stringField("speciesKey"), stringField("habitatInfluence"));
    }catch(UnknownHabitatKeyExceptionCore e) {
      throw new UnknownHabitatKeyException(e.getKey());
    }catch(UnknownSpeciesKeyExceptionCore e){
      throw new UnknownSpeciesKeyException(e.getKey());
    }
  }
}
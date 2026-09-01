package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalKeyExceptionCore;
import hva.core.exception.UnknownVaccineKeyExceptionCore;
import hva.core.exception.UnknownVeterinarianKeyExceptionCore;
import hva.core.exception.VeterinarianNotAuthorizedExceptionCore;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.animal.Prompt;


class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("vaccineKey", hva.app.vaccine.Prompt.vaccineKey());
    addStringField("veterinarianKey", hva.app.vaccine.Prompt.veterinarianKey());
    addStringField("animalKey", Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    try{
      _display.popup(_receiver.vacinarAnimal(stringField("vaccineKey"),stringField("veterinarianKey"), stringField("animalKey")));

    }
      catch ( UnknownVaccineKeyExceptionCore  e){
        throw new  UnknownVaccineKeyException(e.getKey());
      }
      catch ( UnknownVeterinarianKeyExceptionCore  e){
        throw new  UnknownVeterinarianKeyException(e.getKey());
      }catch(VeterinarianNotAuthorizedExceptionCore e){
        throw new VeterinarianNotAuthorizedException(e.getKey(), e.getName());
      }catch(UnknownAnimalKeyExceptionCore e){
        throw new UnknownAnimalKeyException(e.getKey());
      }
  }
}
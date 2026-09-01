package hva.app.main;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.HotelManager;
import hva.core.exception.UnknownAnimalKeyExceptionCore;
import hva.core.exception.UnknownEmployeeKeyExceptionCore;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for show the global satisfation of the current zoo hotel.
 **/
class DoShowGlobalSatisfaction extends Command<HotelManager> {
  DoShowGlobalSatisfaction(HotelManager receiver) {
    super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
  }
  
  @Override
  protected final void execute() throws UnknownAnimalKeyException, UnknownEmployeeKeyException {
    try{
    _display.popup(_receiver.getHotel().calcularSatisfacaoGlobal());
    }catch(UnknownAnimalKeyExceptionCore e){
      throw new UnknownAnimalKeyException(e.getKey());
    }catch (UnknownEmployeeKeyExceptionCore e){
      throw new UnknownEmployeeKeyException(e.getKey());
    }
  }
}
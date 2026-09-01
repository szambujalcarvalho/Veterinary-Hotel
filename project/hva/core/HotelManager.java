package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.ArrayList;

 /**
 * Manage acess to zoo hotel and implement load/save operations.
 **/

public class HotelManager {

  private Hotel _hotel = null;
  private String _fileName = "";

  /**
   * Saves the serialized application's state into the file associated to the
   * current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/

  public void criar() {
    this._hotel = new Hotel();
  }
   /** Guarda o ficheiro*/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_fileName == null || _fileName.equals(""))
      throw new MissingFileAssociationException();
    try (ObjectOutputStream saveFile = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(_fileName)))) {
      saveFile.writeObject(_hotel);
    }
  }

  /**
   * Saves the serialized application's state into the specified file. The current network is associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
   
   /** Guarda o ficheiro com um dado nome*/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _fileName = filename;
    save();
  }

  /**
   * @param filename name of the file containing the serialized application's state to load.
   * @throws UnavailableFileException if the specified file does not exist or there is an error while processing this file.
   **/
   /** Carregar o ficheiro*/
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException {
    _fileName = filename;
    try (ObjectInputStream fileName = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
      this._hotel = (Hotel) fileName.readObject();
    } catch (IOException e) {
      throw new UnavailableFileException(filename);
    }
  }

  /**
   * Read text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entitiesi representeed in the import file.
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the processing of the import file.
   **/
   /** Importa o ficheiro*/
  public void importFile(String filename) throws ImportFileException {
    try {
      getHotel().importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  }

  /**
   * Returns the zoo hotel managed by this instance.
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    if(_hotel == null){
      criar();
    }
    return _hotel;
  }



}
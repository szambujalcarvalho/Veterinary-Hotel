package hva.core;

import hva.app.exception.*;
import hva.core.exception.*;
import java.io.*;
import java.util.*;
import hva.app.vaccine.Message;

public class Hotel implements Serializable {

 
   /** @Serial Número de série para serialização. */
  private static final long serialVersionUID = 202407081733L;

  /** Os animais associados ao hotel. */
  private TreeMap<String, Animal> _animals;

  /** Os habitats associados ao hotel */
  private TreeMap<String, Habitat> _habitats;

  /** Os funcionários associados ao hotel. */
  private TreeMap<String, Funcionarios> _funcionarios;

  /** Os veterinarios associados ao hotel. */
  private TreeMap<String, Veterinario> _veterinarios;

  /** Os tratadores associados ao hotel. */
  private TreeMap<String, Tratador> _tratadores;

  /** As vacinas associadas ao hotel. */
  private TreeMap<String, Vacinas> _vacinas;

  /**As árvores associadas ao hotel*/
  private TreeMap<String, Arvore> _arvores;

  /** As vacinas associadas ao hotel. */
  private TreeSet<String> _speciesId;

  private List<String> _registoVacinação;

  private List<String> _vacinacoesErroneas;


  private Estacao _estaçãoAtual = new Estacao();


  /** 
   * Main Construtor
    */

  public Hotel(){
    _animals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _habitats = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _funcionarios = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _vacinas = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _arvores = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _tratadores = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _veterinarios = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _speciesId = new TreeSet<>();
    _registoVacinação = new ArrayList<>();
    _vacinacoesErroneas = new ArrayList<>();
  }


  /** ##########################
   *   HABITAT
   * ##########################
   */

  /**
   * Regista um habitat no hotel.
   * 
   * @param id o ID do habitat
   * @param nome o nome do habitat
   * @param area a área do habitat
   * @param arvoresId lista de IDs das árvores associadas ao habitat
   * @throws DuplicateHabitatKeyExceptionCore se o ID do habitat já existir
   */
  public void registerHabitat(String id, String nome, int area, List<String> arvoresId) throws DuplicateHabitatKeyExceptionCore{
    String adequacao = "NEU";
    for(Habitat h1: _habitats.values()){
      if(h1.getId().equals(id)){
        throw new DuplicateHabitatKeyExceptionCore(id);
      }
    }
    Habitat habitat = new Habitat(id, nome, area,adequacao, arvoresId);
    _habitats.put(id, habitat);
  }
   
  /**
   * Mostra todos os habitats registados no hotel, incluindo suas árvores.
   * 
   * @return uma lista de strings que representam os habitats
   */
  public List<String> mostrarHabitats(){
    List<String> habitats = new ArrayList<>();
      for(Habitat h1: _habitats.values()){
        habitats.add(h1.toString());
        var habitatTrees = h1.getArvoresId();
        if(habitatTrees!= null){
          for(String tree: habitatTrees ){
            Arvore arvore = _arvores.get(tree);
            habitats.add(arvore.toString());
          }
        }
      }
      return habitats;
  }

  /**
   * Regista um animal no hotel.
   * 
   * @param habitatId o ID do animal
   * @param areaNova o nome do animal
   * @throws UnknownHabitatKeyExceptionCore se o ID do animal já existir
   */
  public void alterarArea(String habitatId, int areaNova) throws UnknownHabitatKeyExceptionCore {
    Habitat habitat = _habitats.get(habitatId);

    if (habitat == null) {
      throw new UnknownHabitatKeyExceptionCore(habitatId); 
    }

    habitat.setArea(areaNova);
}
 
  public int adequacao(String idHabitat){
    int adequacao = 0;
    Habitat habitat = _habitats.get(idHabitat);
    var stringInfluencia = habitat.getAdequacao();
    if(stringInfluencia.equals("NEU")){
      adequacao = 0;
    }
    else if (stringInfluencia.equals("NEG")){
      adequacao = -20;
    }
    else if (stringInfluencia.equals("POS")){
      adequacao = 20;
    }
    return adequacao;
  }

  public void alteraAdequacao(String idHabitat, String idEspecie, String influenciaHabitat) throws UnknownHabitatKeyExceptionCore, UnknownSpeciesKeyExceptionCore{
    if (!_habitats.containsKey(idHabitat)){
      throw new UnknownHabitatKeyExceptionCore(idHabitat);
    }
    if(!_speciesId.contains(idEspecie)){
      throw new UnknownSpeciesKeyExceptionCore(idEspecie);
    }
    Habitat habitat = _habitats.get(idHabitat);

    habitat.setAdequacao(influenciaHabitat, idEspecie);
  }


  public int calculaSatisfacao(String animalId) throws UnknownAnimalKeyExceptionCore {
    if (!_animals.containsKey(animalId)){
      throw new UnknownAnimalKeyExceptionCore(animalId);
    }
    Animal animal = _animals.get(animalId);
    var especie = animal.getEspecieId();
    String habitatDoAnimal = animal.getHabitatId();
    Habitat habitatrequerido = _habitats.get(habitatDoAnimal);
    var especiesInfluencia = habitatrequerido.getEspecies();

    int especieIgual = contarEspeciesIguais(habitatrequerido,animal);

    int especieDiferente = contarEspeciesDiferentes(habitatrequerido,animal);

    int populacao = habitatrequerido.getPopulacao();

    // Área do habitat
    int area = habitatrequerido.getArea();

    // Calcula a adequação do habitat para o animal
    int adequacao = adequacao(habitatDoAnimal); 


    // Fórmula de satisfação
    if(!especiesInfluencia.contains(especie)){
    
      int resultado = 20 + 3 * especieIgual - 2 * especieDiferente + area / populacao;
      return Math.round(resultado);
    }
    else{
      int resultado = 20 + 3 * especieIgual - 2 * especieDiferente + area / populacao + adequacao;
      return Math.round(resultado);
    }
  }


/** ##########################
   *   ESPECIES
   * ##########################
   */


/**
   * Regista uma especie no hotel.
   * 
   * @param id o ID da especie
   * @param nome o nome da especie
   */
  public void registerSpecies(String id, String nome){
    _speciesId.add(id);
    
  }


  public int contarEspeciesIguais(Habitat habitat,Animal animal) {
    int count = 0;
    List<String> idAnimais = habitat.getAnimais();
    for (String a : idAnimais) {
        if (_animals.get(a).getEspecieId().equals(animal.getEspecieId()) && !_animals.get(a).equals(animal)) {
            count++;
        }
    }
    return count;
}

  public int contarEspeciesDiferentes(Habitat habitat,Animal animal) {
    int count = 0;
    List<String> idAnimais = habitat.getAnimais();
    for (String a : idAnimais) {
        if (!_animals.get(a).getEspecieId().equals(animal.getEspecieId())) {
            count++;
        }
    }
    return count;
}


  /** ##########################
   *   ANIMAL
   * ##########################
   */

   /**
   * Regista um animal no hotel.
   * 
   * @param id o ID do animal
   * @param nome o nome do animal
   * @param habitatId o ID do habitat ao qual o animal pertence
   * @param especieId o ID da espécie do animal
   * @throws DuplicateAnimalKeyExceptionCore se o ID do animal já existir
   * @throws UnknownHabitatKeyExceptionCore 
   */
  public void registerAnimal(String id, String nome, String habitatId, String especieId ) throws DuplicateAnimalKeyExceptionCore, UnknownAnimalKeyExceptionCore, UnknownHabitatKeyExceptionCore{
    for(Animal a1: _animals.values()){
      if(a1.getId().equals(id)){
        throw new DuplicateAnimalKeyExceptionCore(id);
      }
    }
    
    if(_habitats.get(habitatId) == null){
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }

    if (!_speciesId.contains(especieId)) {
      _speciesId.add(especieId);

  }
    Animal animal = new Animal(id, nome, habitatId, especieId);
    _animals.put(id, animal);
    _habitats.get(habitatId).addAnimal(id);
  }
  
  /**
   * Mostra todos os animais registados no hotel.
   * 
   * @return uma lista de strings que representam os animais
   */
  public List<String> mostrarAnimais(){
    List<String> animais = new ArrayList<>();
      for(Animal a1: _animals.values()){
        animais.add(a1.toString());
      }
      return animais;
  }

  /**
   * Transfere um animal de um habitat para outro.
   * 
   * @param animalId o ID do animal a ser transferido
   * @param habitatDestino o ID do habitat de destino
   * @throws DuplicateAnimalKeyExceptionCore se o animal já estiver no habitat de destino
   * @throws UnknownAnimalKeyExceptionCore se o animal não for encontrado
   * @throws UnknownHabitatKeyExceptionCore se o habitat de destino não for encontrado
   */
  public void transferirAnimal(String animalId, String habitatDestino) throws DuplicateAnimalKeyExceptionCore, UnknownAnimalKeyExceptionCore, UnknownHabitatKeyExceptionCore {
    Animal animalToTransfer = null;
    Habitat habitatDest = null;
    Habitat habitatIni = null;

    for (Animal a1 : _animals.values()) {
        if (a1.getId().equals(animalId)) {
            animalToTransfer = a1; 
            break; 
        }
    }

    if (animalToTransfer == null) {
        throw new UnknownAnimalKeyExceptionCore(animalId);
    }

    for (Habitat h1 : _habitats.values()){
        if (h1.getId().equals(habitatDestino)) {
            habitatDest = h1; 
            break; 
        }    
    }

    if (habitatDest == null) {
        throw new UnknownHabitatKeyExceptionCore(habitatDestino);
    }

    if (habitatDest.getAnimais().contains(animalId)) {
        throw new DuplicateAnimalKeyExceptionCore(animalId);
    }
    
    habitatDest.addAnimal(animalId);

    String habitatOriginalId = animalToTransfer.getHabitatId();
    animalToTransfer.setHabitat(habitatDestino);


    for (Habitat h1 : _habitats.values()) {
        if (h1.getId().equals(habitatOriginalId)) {
            habitatIni = h1; 
            break;
        }
    }

    if (habitatIni != null) {
        habitatIni.removeAnimal(animalId);
    }
  }


  /** ##########################
   *   FUNCIONARIOS
   * ##########################
   */

   /**
   * Regista um funcionário no hotel.
   * 
   * @param id o ID do funcionário
   * @param nome o nome do funcionário
   * @param funcao a função do funcionário (VET ou TRT)
   * @param responsabilidade a lista de responsabilidades do funcionário
   * @throws DuplicateEmployeeKeyExceptionCore se o ID do funcionário já existir
   */
  public void registerEmployee(String id, String nome, String funcao, List<String> responsabilidade) throws DuplicateEmployeeKeyExceptionCore{
    for(Funcionarios f1: _funcionarios.values()){
      if(f1.getId().equals(id)){
        throw new DuplicateEmployeeKeyExceptionCore(id);
      }
    }

    if (funcao.equals("VET")){
      if(responsabilidade == null){
        Veterinario veterinario = new Veterinario(id, nome, null);
        _funcionarios.put(id, veterinario);
        _veterinarios.put(id, veterinario);
      }else{
        Veterinario veterinario = new Veterinario(id, nome, responsabilidade);
        _funcionarios.put(id, veterinario);
        _veterinarios.put(id, veterinario);
      }
    }

    if (funcao.equals("TRT")){
      if (responsabilidade == null){
        Tratador tratador = new Tratador(id, nome, null);
        _funcionarios.put(id,tratador);
        _tratadores.put(id, tratador);
      }else{
        Tratador tratador = new Tratador(id, nome, responsabilidade);
        _funcionarios.put(id,tratador);
        _tratadores.put(id, tratador);
      }
    }
  }
   
  /**
   * Mostra todos os funcionários registados no hotel.
   * 
   * @return uma lista de strings que representam os funcionários
   */
  public List<String> mostrarFuncionarios(){
    List<String> funcionarios = new ArrayList<>();
      for(Funcionarios f1: _funcionarios.values()){
        funcionarios.add(f1.toString());
      }
      
      return funcionarios;
  }

  /**
   * adiciona responsabilidade ao hotel.
   * 
   * @param funcionarioId o ID do funcionario
   * @param responsabilidadeId id da responsabilidade
   * @throws NoResponsibilityExceptionCore  se o ID da vacina já existir
   */
  public void addResponsabilidade(String funcionarioId, String responsabilidadeId) throws UnknownEmployeeKeyExceptionCore {
    Funcionarios funcionario = _funcionarios.get(funcionarioId);
    if (funcionario == null) {
        throw new UnknownEmployeeKeyExceptionCore (funcionarioId); 
    }

    funcionario.addResponsabilidade(responsabilidadeId);
}

  /**
   * Adiciona uma responsabilidade ao hotel.
   * 
   * @param funcionarioId o ID do funcionario
   * @param responsabilidadeId id da responsabilidade
   * @throws NoResponsibilityExceptionCore  se o ID da vacina já existir
   */
  public void removeResponsabilidade(String funcionarioId, String responsabilidadeId) throws NoResponsibilityExceptionCore, UnknownEmployeeKeyExceptionCore {
    Funcionarios funcionario = _funcionarios.get(funcionarioId);
    if (funcionario == null) {
        throw new UnknownEmployeeKeyExceptionCore(funcionarioId); 
    }

    if (!funcionario.getIdResponsabilidades().contains(responsabilidadeId)) {
        throw new NoResponsibilityExceptionCore(funcionarioId, responsabilidadeId);
    }

    funcionario.removeResponsabilidade(responsabilidadeId);
  }
  
  /** 
  * Calcula a satisfação de um funcionario. 
  * @param funcionarioId O identificador do funcionario cuja satisfação será calculada.
  * @return A satisfação do funcionario como um número inteiro arredondado.
  * @throws UnknownEmployeeKeyExceptionCore Se o funcionario com o ID fornecido não existir no sistema.
  */
  public int calcularSatisfacaoFuncionario(String funcionarioId) throws UnknownEmployeeKeyExceptionCore {
    if (_funcionarios.get(funcionarioId) == null) {
      throw new UnknownEmployeeKeyExceptionCore(funcionarioId);
    }

    if (_veterinarios.get(funcionarioId) != null) {
      double satisfacao = 20.0;
      Veterinario veterinario = _veterinarios.get(funcionarioId);

      for (String especieVeterinario : veterinario.getIdResponsabilidades()) {
        int populacao = 0;
        int numVeterinarios = 1; 

        for (Animal animal : _animals.values()) {
          if (animal.getEspecieId().equals(especieVeterinario)) {
              populacao++;
          }
        }

        for (Veterinario vet : _veterinarios.values()) {
          if (vet.getIdResponsabilidades().contains(especieVeterinario) && vet != veterinario) {
              numVeterinarios++;
          }
        }

        // Calcula a satisfação para essa espécie
        satisfacao -= (double) populacao / numVeterinarios;
      }

      return (int) Math.round(satisfacao);
    } else if (_tratadores.get(funcionarioId) != null) {
      Tratador tratador = _tratadores.get(funcionarioId);
      int satisfacao = 300; 


      for (Habitat habitat : _habitats.values()) {
      if (tratador.getIdResponsabilidades().contains(habitat.getId())) {
        int area = habitat.getArea();
        int populacao = habitat.getPopulacao();
        int numTratadores = 0;

        for (Tratador t : _tratadores.values()) {
            if (t.getIdResponsabilidades().contains(habitat.getId())) {
                numTratadores++;
            }
        }

        int esforcoLimpezaTotal = 0;
        for (String arvoreId : habitat.getArvoresId()) {
            Arvore arvore = _arvores.get(arvoreId);
            esforcoLimpezaTotal += arvore.calcularEsforcoLimpeza(); 
        }

        int trabalhoNoHabitat = area + (3 * populacao) + esforcoLimpezaTotal;      
        satisfacao -= trabalhoNoHabitat / numTratadores;
        }
      }

      return satisfacao; 
    }
    return 0;
  }

  
  /** ##########################
   *   VACINAS
   * ##########################
   */

  /**
   * Regista uma vacina no hotel.
   * 
   * @param id o ID da vacina
   * @param nome o nome da vacina
   * @param speciesIds os IDs das espécies às quais a vacina se aplica
   * @throws DuplicateVaccineKeyExceptionCore se o ID da vacina já existir
   */
  public void registerVaccine(String id, String nome, String[] speciesIds) throws DuplicateVaccineKeyExceptionCore, UnknownSpeciesKeyExceptionCore{
    int numeroAplicacoes = 0;
    if(_vacinas.containsKey(id)){
      throw new DuplicateVaccineKeyExceptionCore(id);
    }

    for (String specieId : speciesIds){
      if (!_speciesId.contains(specieId)) {
        throw new UnknownSpeciesKeyExceptionCore(specieId);
      }
    }

    Vacinas vacina = new Vacinas(id, nome, numeroAplicacoes, speciesIds);
    _vacinas.put(id, vacina); 
  }


   /**
   * Mostra todas as vacinas registadas no hotel.
   * 
   * @return uma lista de strings que representam as vacinas
   */
  public List<String> mostrarVacinas(){
    List<String> vacinas = new ArrayList<>();
      for(Vacinas v1: _vacinas.values()){
        vacinas.add(v1.toString());
      }
      
      return vacinas;
  }


  /**
   * vacinar um animal.
   * 
   * @param idVacina o ID da vacina
   * @param idFuncionario o id do funcionario
   * @param idAnimal o id do animal
   * @throws UnknownAnimalKeyExceptionCore se o ID do animal nao existir
   * @throws UnknownVeterinarianKeyExceptionCore se o ID do veterinario não existir
   * @throws UnknownVaccineKeyExceptionseCore o ID da vacina não existir
   * @throws VeterinarianNotAuthorizedExceptionCore se o veterinario nao tiver essa vacina
   */
  public String vacinarAnimal(String idVacina, String idFuncionario, String idAnimal)throws UnknownAnimalKeyExceptionCore, UnknownVeterinarianKeyExceptionCore,UnknownVaccineKeyExceptionCore,VeterinarianNotAuthorizedExceptionCore{
    if (_vacinas.get(idVacina) == null) {
      throw new UnknownVaccineKeyExceptionCore(idVacina);
  }

    if (_veterinarios.get(idFuncionario) == null) {
        throw new UnknownVeterinarianKeyExceptionCore(idFuncionario);
    }

    List<String> responsabilidades = _veterinarios.get(idFuncionario).getIdResponsabilidades();
    String especieIdAnimal = _animals.get(idAnimal).getEspecieId();

    boolean autorizado = false;
    for (String responsabilidade : responsabilidades) {
        if (responsabilidade.equals(especieIdAnimal)) {
            autorizado = true;
            break;
        }
    }

    if (!autorizado) {
        throw new VeterinarianNotAuthorizedExceptionCore(idFuncionario, especieIdAnimal);
    }

    Animal animal = _animals.get(idAnimal);
    if (animal == null) {
        throw new UnknownAnimalKeyExceptionCore(idAnimal);
    }

    String[] especiesPermitidas = _vacinas.get(idVacina).getIDEspecies();
    boolean vacinaAdequada = false;
    for (String especie : especiesPermitidas) {
        if (especie.equals(especieIdAnimal)) {
            vacinaAdequada = true;
            break;
        }
    }

    int dano;
    String mensagem = "";
    if (!vacinaAdequada) { 
      mensagem = Message.wrongVaccine(idVacina, idAnimal);
      dano = calcularDanoVacina(_vacinas.get(idVacina), _animals.get(idAnimal));  
    } else {
      dano = -1;  
    }

    String estadoSaude = determinarEstadoSaude(dano);
    animal.historialSaude(estadoSaude);  

    VacinaAplicacao registro = new VacinaAplicacao(idVacina, idFuncionario, _animals.get(idAnimal).getEspecieId());
    _registoVacinação.add(registro.toString());
    _vacinas.get(idVacina).aumentaAplicacao();
    _animals.get(idAnimal).addVacinacao(registro.toString());
    _veterinarios.get(idFuncionario).addVacinacao(registro.toString());
    if (dano!= -1){
      _vacinacoesErroneas.add(registro.toString());
    }
    return mensagem;
  }

  public List<String> mostrarVacinacoes(){
    return _registoVacinação;
  }

  /**
   * Calcula o dano causado.
   * 
   * @param vacina que causa o dano
   * @param animal que é danificado
   */
  public int calcularDanoVacina(Vacinas vacina, Animal animal) {
    int maxTamanho = 0;
    String especieAnimal = animal.getEspecieId();

    for (String especieVacina : vacina.getIDEspecies()) {
        int tamanho = Math.max(especieAnimal.length(), especieVacina.length());
        maxTamanho = Math.max(maxTamanho, tamanho);
        if(especieVacina.equals(especieAnimal)){
          return -1;
        }
    }
    int caracteresComuns = contarCaracteresComuns(especieAnimal, vacina.getIDEspecies()[0]);

    return maxTamanho - caracteresComuns;
  }


  /**
   * Compara os caracteres em comum de duas especies
   * 
   * @param especie1 
   * @param especie2
   */
  public int contarCaracteresComuns(String especie1, String especie2) {
    int contador = 0;
    for (char c : especie1.toCharArray()) {
        if (especie2.indexOf(c) != -1) {
            contador++;
        }
    }
    return contador;
  }


  /**
   * determina o estado de saude
   * 
   * @param dano dano causado pela vacina
   */
  public String determinarEstadoSaude(int dano) {
    if (dano == 0) {
        return "CONFUSÃO";
    } else if (dano >= 1 && dano <= 4) {
        return "ACIDENTE";
    }else if (dano == -1){
      return "NORMAL";
    } else {
        return "ERRO";
    }
  }


  /** ##########################
   *   ARVORES
   * ##########################
   */

  /**
   * Cria uma árvore no hotel.
   * 
   * @param id o ID da árvore
   * @param nome o nome da árvore
   * @param tipoFolha o tipo de folha da árvore (CADUCA ou PERENE)
   * @param idade a idade da árvore
   * @param dificuldadeLimpeza a dificuldade de limpar a árvore
   * @throws DuplicateTreeKeyExceptionCore se o ID da árvore já existir
   */
  public void createTree (String id, String nome, String tipoFolha, int idade , int dificuldadeLimpeza) throws DuplicateTreeKeyExceptionCore{
    for (Arvore a1: _arvores.values()){
      if(a1.getId().equals(id)){
        throw new DuplicateTreeKeyExceptionCore(id);
      }
    }
    
    if(tipoFolha.equals("CADUCA")){
      ArvoreCaduca arvore = new ArvoreCaduca(id, nome, dificuldadeLimpeza, idade);
      _arvores.put(id,arvore);
    }

    else if(tipoFolha.equals("PERENE")){
      ArvorePerenne arvore = new ArvorePerenne(id, nome, dificuldadeLimpeza, idade);
      _arvores.put(id,arvore);
    }
  }

  
  /**
   * Planta uma árvore num habitat.
   * 
   * @param id o ID da árvore
   * @param nome o nome da árvore
   * @param tipoFolha o tipo de folha da árvore (CADUCA ou PERENE)
   * @param idade a idade da árvore
   * @param dificuldadeLimpeza a dificuldade de limpar a árvore
   * @param habitatId o ID do habitat onde a árvore será plantada
   * @return 
   * @throws DuplicateTreeKeyExceptionCore se o ID da árvore já existir
   * @throws UnknownHabitatKeyExceptionCore se o habitat não for encontrado
   */
  public String plantTree(String id, String nome, String tipoFolha, int idade , int dificuldadeLimpeza, String habitatId) throws DuplicateTreeKeyExceptionCore, UnknownHabitatKeyExceptionCore{
    Habitat habitatPlantar = null;
    createTree(id, nome, tipoFolha, idade, dificuldadeLimpeza);
    for (Habitat h1: _habitats.values()){
      if(h1.getId().equals(habitatId)){
        habitatPlantar = h1;
        break;
      }
    }

    if (habitatPlantar == null) {
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }
    
    habitatPlantar.addArvoreId(id);  

    if(tipoFolha.equals("CADUCA")){
      ArvoreCaduca arvore = new ArvoreCaduca(id, nome, dificuldadeLimpeza, idade);
      return arvore.toString();
    }else if( tipoFolha.equals("PERENE")){
      ArvorePerenne arvore1 = new ArvorePerenne(id, nome, dificuldadeLimpeza, idade);
      return arvore1.toString();
    }
    return null;
  }

  
  /**
   * Mostra todas as árvores de um habitat específico.
   * 
   * @param habitatId o ID do habitat
   * @return uma lista de strings que representam as árvores
   * @throws UnknownHabitatKeyExceptionCore se o habitat não for encontrado
   */
  public List<String> mostrarArvores(String habitatId)throws UnknownHabitatKeyExceptionCore{
    Habitat habitatArvores = null;
    for(Habitat habitat: _habitats.values()){
      if (habitat.getId().equals(habitatId)){
        habitatArvores = habitat;
        break;
      }
    }

    if (habitatArvores == null) {
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }

    List<String> arvores = new ArrayList<>();
    var habitatArvores1 = habitatArvores.getArvoresId();
    if (habitatArvores1!= null){
      for(String arvore: habitatArvores1){
        Arvore arvore1 = _arvores.get(arvore);
        arvores.add(arvore1.toString());
      }
    }

    return arvores;
  }


  /** 
  * avança a estação do ano. 
  *
  * @return a nova estação em que se encontra.
  */
  public int avancarEstacao() {
    int novaEstacao = _estaçãoAtual.proximaEstacao();

    for (Arvore arvore : _arvores.values()) {
        arvore.incrementarIdadeSeForEstacaoPlantio(novaEstacao);
    }

    return novaEstacao;
  }


  /** 
  * Calcula a satisfação global.
  * 
  * @return A satisfação global como um número inteiro arredondado.
  * @throws UnknownEmployeeKeyExceptionCore Se o funcionario com o ID fornecido não existir no sistema.
  * @throws UnknownAnimalKeyExceptionCore se o id do animal nao existir no sistema
  */
  public int calcularSatisfacaoGlobal() throws UnknownAnimalKeyExceptionCore,UnknownEmployeeKeyExceptionCore, UnknownEmployeeKeyExceptionCore{
    int satisfacao = 0;
    for (Animal a: _animals.values()){
      String animalId = a.getId();
      satisfacao += calculaSatisfacao(animalId);
    }

    for (Funcionarios f: _funcionarios.values()){
      String funcionarioId = f.getId();
      satisfacao += calcularSatisfacaoFuncionario(funcionarioId);
    }

    return satisfacao;
  }


/** ##########################
   *   CONSULTAS
   * ##########################
   */
  
  /**
   * Mostra os animais de um habitat.
   *
   * @param habitatId o ID do habitat ao qual o animal pertence
   * @throws UnknownHabitatKeyExceptionCore 
   * @return lista de strings de animais
   */  
  public List<String> mostrarAnimaisHabitat(String habitatId) throws UnknownHabitatKeyExceptionCore{
    if(_habitats.get(habitatId) == null){
      throw new UnknownHabitatKeyExceptionCore(habitatId);
    }

    List<String> idAnimais = _habitats.get(habitatId).getAnimais();
    List<String> animais = new ArrayList<>();
    Collections.sort(idAnimais);
    for (String id: idAnimais){
      animais.add(_animals.get(id).toString());
    }

    return animais;
  }


  /**
   * Mostra os atos medicos a um animal.
   *
   * @param animalId o ID do animal
   * @throws UnknownAnimalKeyExceptionCore
   * @return lista de strings de atos medicos
   */  
  public List<String> atosMedicosAnimal(String animalId) throws UnknownAnimalKeyExceptionCore{
    if(_animals.get(animalId) == null){
      throw new UnknownAnimalKeyExceptionCore(animalId);
    }
    return _animals.get(animalId).getVacinacoes() != null 
           ? _animals.get(animalId).getVacinacoes() 
           : new ArrayList<>();
  }


  /**
   * Mostra os atos medicos veterinarios.
   *
   * @param animalId o ID do animal
   * @throws UnknownVeterinarianKeyExceptionCore 
   * @return lista de strings de atos medicos
   */  
  public List<String> atosMedicosVeterinario(String funcionarioId)throws UnknownVeterinarianKeyException{
    if(_funcionarios.get(funcionarioId) == null){
      throw new UnknownVeterinarianKeyException(funcionarioId);
    }

    return _veterinarios.get(funcionarioId).getVacinacoes() != null 
           ? _veterinarios.get(funcionarioId).getVacinacoes() 
           : new ArrayList<>();

  }

  
  /**
   * Mostra os registos das vacinas que causaram dano.
   *
   * @return lista de strings das vacinas erroneas
   */  
  public List<String> vaciacoesErroneas(){
    return _vacinacoesErroneas;
  }


  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }
}

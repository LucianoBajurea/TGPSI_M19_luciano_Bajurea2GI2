package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.tgpsi_m19_luciano_bajurea.ConexaoBD.*;

public class PrincipalController implements Initializable {
    // Atributos privados do principal.FXML
    @FXML
    private TableColumn tableColumnDesc;
    @FXML
    private TextField productSearch;
    @FXML
    private Button btnSeeDetailProduct;
    @FXML
    private Button btnFornecedor;
    @FXML
    private TextField clientSearch;
    @FXML
    private TextField clientNIF;
    @FXML
    private AnchorPane pesquisarCliente;
    @FXML
    private AnchorPane AnchorPaneMain;
    @FXML
    private Button btnAbout;
    @FXML
    private Button btnClient;
    @FXML
    private Button btnSeller;
    @FXML
    private Button btnProduct;
    @FXML
    private AnchorPane AnchorPaneProduct;
    @FXML
    private TableView tableViewProduct;
    @FXML
    private TableColumn tableColumnIdProduct;
    @FXML
    private TableColumn tableColumnNameProduct;
    @FXML
    private TableColumn tableColumnTypeProduct;
    @FXML
    private TableColumn tableColumnPriceProduct;
    @FXML
    private TextField productId;
    @FXML
    private TextField productName;
    @FXML
    private ComboBox productCategoria;
    @FXML
    private TextField productPrice;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnEdit;
    @FXML
    private AnchorPane AnchorPaneAbout;
    @FXML
    private AnchorPane AnchorPaneClient;
    @FXML
    private TableView tableViewClient;
    @FXML
    private TableColumn tableColumnIdClient;
    @FXML
    private TableColumn tableColumnNameClient;
    @FXML
    private TableColumn tableColumnNifClient;
    @FXML
    private TableColumn tableColumnAdressClient;
    @FXML
    private TableColumn tableColumnNumTelClient;
    @FXML
    private TableColumn tableColumnEmailCliente;
    @FXML
    private TextField clientId;
    @FXML
    private TextField clientName;
    @FXML
    private TextField clientNumTel;
    @FXML
    private TextField clientEmail;
    @FXML
    private Button btnAddClient;
    @FXML
    private Button btnRemoveCliente;
    @FXML
    private Button btnEditClient;
    @FXML
    private TextField clientAdress;
    @FXML
    private AnchorPane AnchorPaneFornecedor;
    @FXML
    private TableView tableViewFornecedor;
    @FXML
    private TableColumn tableColumnIdForn;
    @FXML
    private TableColumn tableColumnNameForn;
    @FXML
    private TableColumn tableColumnNifForn;
    @FXML
    private TableColumn tableColumnAdressForn;
    @FXML
    private TableColumn tableColumnNumTelForn;
    @FXML
    private TableColumn tableColumnEmailForn;
    @FXML
    private TextField FornecedorSearch;
    @FXML
    private TextField fornecedorId;
    @FXML
    private TextField fornecedorName;
    @FXML
    private TextField fornecedorNIF;
    @FXML
    private TextField fornecedorAdress;
    @FXML
    private TextField fornecedorNumTel;
    @FXML
    private TextField fornecedorEmail;
    @FXML
    private Button btnFornecedorAdd;
    @FXML
    private Button btnFornecedorRemove;
    @FXML
    private Button btnFornecedorEdit;
    @FXML
    private Button btnDetailForn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fornecedorListar();
        InventoryTypeProduct();
        clienteListar();
        ProductListar();

    }

    public void buttonOnClient(ActionEvent actionEvent) {
        // Oculta os painéis relacionados a Clientes, Vendedores e Informações "Acerca de..."
        // Torna visível o painel relacionado ao Cliente
        AnchorPaneClient.setVisible(true);
        AnchorPaneFornecedor.setVisible(false);
        AnchorPaneAbout.setVisible(false);
        AnchorPaneProduct.setVisible(false);
    }

    public void buttonFornecedor(ActionEvent actionEvent) {
        // Oculta os painéis relacionados a Clientes, Informações "Acerca de..." e Produtos
        // Torna visível o painel relacionado o fornecedor
        AnchorPaneClient.setVisible(false);
        AnchorPaneFornecedor.setVisible(true);
        AnchorPaneAbout.setVisible(false);
        AnchorPaneProduct.setVisible(false);
    }

    public void buttonOnProduct(ActionEvent actionEvent) {
        // Oculta os painéis relacionados a Clientes, Informações "Acerca de..." e Produtos
        // Torna visível o painel relacionado o Vendedor
        AnchorPaneClient.setVisible(false);
        AnchorPaneFornecedor.setVisible(false);
        AnchorPaneAbout.setVisible(false);
        AnchorPaneProduct.setVisible(true);
    }

    public void buttonAbout(ActionEvent actionEvent) {
        // Oculta os painéis relacionados a Cliente, Fornecedor e Produtos
        // Torna visível o painel relacionado a Acerca de
        AnchorPaneClient.setVisible(false);
        AnchorPaneFornecedor.setVisible(false);
        AnchorPaneAbout.setVisible(true);
        AnchorPaneProduct.setVisible(false);

    }

    /* Ao clicar no botão fechar aparece um alerta a questionar se desejo sair,
     * apos ao clicar que sim fecha-me o programa
     */
    public void buttonCloseApp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair da aplicação");
        alert.setHeaderText("Deseja mesmo sair da apliação?");
        // Adiciona botões personalizados em português
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não");
        alert.getButtonTypes().setAll(botaoSim, botaoNao);

        alert.showAndWait().ifPresent(response -> {
            if (response == botaoSim) {
                Settings.getPrimaryStage().close();
            }
        });
    }

    //-------------------------------------------------------------------------------
    // PRODUTO

    // Declaração de um array de strings que contém os tipos de produtos disponíveis
    private String[] typeProductCombo = {"Roupa", "Acessórios", "Automóvel","Eletrónica","Eletrodómesticos"};

    // Método para inicializar os itens do ComboBox de categorias de produtos
    public void InventoryTypeProduct(){
        // Cria uma lista para guardar os tipos de produtos
        List<String> ListP = new ArrayList<>();

        // Adiciona cada tipo de produto do array à lista
        for(String data : typeProductCombo){
            ListP.add(data);
        }

        // Converte a lista para um ObservableList, que é necessário para o ComboBox no JavaFX
        ObservableList listData =FXCollections.observableArrayList(ListP);

        // Define os itens do ComboBox de categorias de produtos como o ObservableList criado
        productCategoria.setItems(listData);
    }

    public void verProdutos(MouseEvent event) {
        // Obtém o objeto Produto selecionado na tabela
        Produto ProductDataVer = (Produto) tableViewProduct.getSelectionModel().getSelectedItem();

        // Define os campos da interface gráfica com os dados do produto selecionado
        productId.setText(String.valueOf(ProductDataVer.getIdProduto()));
        productName.setText(String.valueOf(ProductDataVer.getNomeProduto()));

        // Define o valor selecionado no ComboBox com o tipo de produto do objeto Produto
        productCategoria.setValue(ProductDataVer.getDescricaoProduto());

        // Define o campo de preço com o valor do preço do objeto Produto
        productPrice.setText(String.valueOf(ProductDataVer.getPrecoProduto()));
    }

    public void pesquisarProduto(KeyEvent keyEvent) {
        // Cria um objeto FilteredList para filtrar a lista
        FilteredList<Produto> filter = new FilteredList<>(Settings.listProduct, e -> true);

        // Adiciona um listener ao texto da barra de pesquisa
        productSearch.textProperty().addListener((Observable, oldValue, newValue) ->{

            filter.setPredicate(predicateProduct ->{
                // Verifica se o novo valor da pesquisa é nulo ou vazio
                if(newValue == null && newValue.isEmpty()){
                    return true;
                }
                // Converte o valor da pesquisa para minúsculas
                String pesquisarProduto = newValue.toLowerCase();

                // Verifica se o ID do Produto contém o valor da pesquisa
                if (String.valueOf(predicateProduct.getIdProduto()).contains(pesquisarProduto)){
                    return true;
                    // Verifica se o nome do Produto contém o valor da pesquisa
                }else if (predicateProduct.getNomeProduto().toLowerCase().contains(pesquisarProduto)) {
                    return true;
                    // Verifica se o preço do Produto contém o valor da pesquisa
                } else if (String.valueOf(predicateProduct.getPrecoProduto()).contains(pesquisarProduto)) {
                    return true;
                }
                // Retorna falso se nenhum critério for atendido
                return false;
            });
        });
        SortedList<Produto>sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(tableViewProduct.comparatorProperty());
        tableViewProduct.setItems(sortedList);
    }

    public void buttonAdd(ActionEvent actionEvent) {
        // Verifica se algum dos campos essenciais está vazio
        if (productName.getText().isEmpty() || productCategoria.getSelectionModel().getSelectedItem() == null || productPrice.getText().isEmpty()) {
            // Mostra um alerta de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Por favor, preencha todos os campos obrigatórios.");
            alert.setContentText("Clique no botão para tentar novamente!");
            alert.showAndWait();
        } else {
            try {
                Connection conn = ConexaoBD.openDB();
                if (conn != null) {
                    // Se tudo estiver correto, obtém os detalhes do novo produto
                    String newName = productName.getText();
                    double newPrice = Double.parseDouble(productPrice.getText());
                    String newCategoria = String.valueOf(productCategoria.getSelectionModel().getSelectedItem());

                    // Cria um alerta de confirmação para a adição do produto
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAR");
                    alert.setHeaderText("Deseja mesmo adicionar este produto?");
                    alert.setContentText("Nome: " + newName + "\nPreço: " + newPrice + "\nCategoria: " + newCategoria + "\nPreço" + newPrice + "\nDeseja mesmo adicionar?");
                    ButtonType buttonSim = new ButtonType("Sim");
                    ButtonType buttonNao = new ButtonType("Não");
                    alert.getButtonTypes().setAll(buttonSim, buttonNao);

                    // Mostra o alerta de confirmação e aguarda a escolha do utilizador
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == buttonSim) {
                            // Tenta adicionar o novo produto ao banco de dados
                            try {
                                String sql = "INSERT INTO produto (nomeProduto, precoProduto, descricaoProduto) VALUES (?, ?, ?)";
                                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                stmt.setString(1, newName);
                                stmt.setDouble(2, newPrice);
                                stmt.setString(3, newCategoria);
                                stmt.executeUpdate();

                                // Recupera o ID gerado automaticamente
                                ResultSet generatedKeys = stmt.getGeneratedKeys();
                                int newId = 0;
                                if (generatedKeys.next()) {
                                    newId = generatedKeys.getInt(1);
                                }

                                // Mostra um alerta informativo sobre a adição bem-sucedida
                                Alert alertAddProduct = new Alert(Alert.AlertType.INFORMATION);
                                alertAddProduct.setTitle("INFORMAÇÃO");
                                alertAddProduct.setHeaderText(null);
                                alertAddProduct.setContentText("Produto inserido com sucesso!");
                                alertAddProduct.showAndWait();

                                // Adiciona o produto à tabela
                                Produto produto = new Produto(newId, newName, newPrice, newCategoria);
                                tableViewProduct.getItems().add(produto);

                                // Limpa os campos após a adição bem-sucedida
                                productName.clear();
                                productPrice.clear();
                                productCategoria.getSelectionModel().clearSelection();

                            } catch (SQLException e) {
                                // Mostra um alerta de erro se ocorrer um erro ao adicionar o produto ao banco de dados
                                Alert alertError = new Alert(Alert.AlertType.ERROR);
                                alertError.setTitle("ERRO");
                                alertError.setHeaderText(null);
                                alertError.setContentText("Erro ao adicionar o produto: " + e.getMessage());
                                alertError.showAndWait();
                            }
                        }
                    });
                } else {
                    // Mostra um alerta de erro se não for possível abrir a conexão com o banco de dados
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setHeaderText(null);
                    alert.setContentText("Não foi possível conectar ao banco de dados.");
                    alert.showAndWait();
                }
            } finally {
                ConexaoBD.closeDB(); // Fecha a conexão com o banco de dados após o uso
            }
        }
    }

    public void buttonRemove(ActionEvent actionEvent)  {
        // Verifica se algum dos campos obrigatórios está vazio
        if (productName.getText().isEmpty()  || productPrice.getText().isEmpty()|| productCategoria.getSelectionModel().getSelectedItem() == null) {
            // Mostra um alerta de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Nenhum Produto selecionado!");
            alert.setContentText("Selecione um Produto para remover.");
            alert.showAndWait();
        } else {
            // Obtém a conexão com a base de dados
            Connection conn = ConexaoBD.openDB();
            if (conn != null) {
                // Obtém os valores dos campos do formulário
                String newName = productName.getText();
                String newCategoria = String.valueOf(productCategoria.getSelectionModel().getSelectedItem());
                double newPrice = Double.parseDouble(productPrice.getText());

                // Mostra um alerta de confirmação para a remoção do produto
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMAR");
                alert.setHeaderText("Deseja mesmo remover este Produto?");
                alert.setContentText("Nome: " + newName + "\nPreço: " + newPrice + "\nCategoria: " + newCategoria + "\nPreço" + newPrice + "\nDeseja mesmo adicionar?");
                ButtonType buttonSim = new ButtonType("Sim");
                ButtonType buttonNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(buttonSim, buttonNao);

                // Aguarda a escolha do utilizador
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.isPresent() && choose.get() == buttonSim) {
                    // Obtém o ID do produto a ser removido
                    int id = Integer.parseInt(productId.getText());
                    // Remove o produto da base de dados usando o DAO
                    ProdutoDAO.removerProduto(id);
                    //System.out.println(Settings.getListForn().size());

                    // Remove o produto da tabela
                    Produto produtoRemovido = (Produto) tableViewProduct.getSelectionModel().getSelectedItem();
                    tableViewProduct.getItems().remove(produtoRemovido);

                    // Limpa os campos após a remoção
                    productName.clear();
                    productPrice.clear();
                    productCategoria.getSelectionModel().clearSelection();

                    // Mostra um alerta de remoção bem-sucedida
                    Alert alertRmProduto = new Alert(Alert.AlertType.INFORMATION);
                    alertRmProduto.setTitle("INFORMAÇÃO");
                    alertRmProduto.setHeaderText(null);
                    alertRmProduto.setContentText("Produto removido com sucesso!");
                    alertRmProduto.showAndWait();
                }
            } else {
                // Mostra um alerta se a conexão com a base de dados falhar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível conectar à base de dados.");
                alert.showAndWait();
            }
        }
    }

    public void buttonEdit(ActionEvent actionEvent) {
        // Verifica se algum dos campos obrigatórios está vazio
        if (productName.getText().isEmpty() || productCategoria.getSelectionModel().getSelectedItem() == null || productPrice.getText().isEmpty()) {
            // Mostra um alerta de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setHeaderText("Por favor,coloque todos os dados nos respetivos campos");
            alert.setContentText("Clique no botão para tentar novamente!");
            alert.showAndWait();
        } else {
            // Obtém o ID do produto a partir do campo de texto
            int newProductId = Integer.parseInt(productId.getText());
            Produto produtoEdit = null;

            // Procura o produto na lista de produtos com o ID do produto
            for (Produto p : Settings.getListProduct()) {
                if (p.getIdProduto() == newProductId) {
                    produtoEdit = p;
                    break;
                }
            }

            // Se o produto foi encontrado, atualiza os seus atributos
            if (produtoEdit != null) {
                produtoEdit.setNomeProduto(productName.getText());
                produtoEdit.setDescricaoProduto((String) productCategoria.getSelectionModel().getSelectedItem());
                produtoEdit.setPrecoProduto(Double.parseDouble(productPrice.getText()));

                // Mostra um alerta de confirmação a questionar se deseja realmente editar o produto
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Editar Fornecedor");
                alert.setHeaderText("Deseja realmente editar?");
                alert.setContentText("Clique no botão para continuar!");
                ButtonType buttonSim = new ButtonType("Sim");
                ButtonType buttonNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(buttonSim, buttonNao);

                // Aguarda a escolha do utilizador
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.isPresent() && choose.get() == buttonSim) {
                    // Obtém a conexão com a base de dados
                    Connection conn = null;
                    try {
                        conn = ConexaoBD.openDB();
                        if (conn != null) {
                            // Cria o SQL de atualização
                            String sql = "UPDATE produto SET nomeProduto = ?, descricaoProduto = ?, precoProduto = ? WHERE idProduto = ?";
                            // Define os parâmetros do SQL
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, productName.getText());
                            stmt.setString(2, (String) productCategoria.getSelectionModel().getSelectedItem());
                            stmt.setString(3, productPrice.getText());
                            stmt.setInt(4, newProductId)    ;

                            // Executa a atualização e verifica se alguma linha foi afetada
                            int rowsAffected = stmt.executeUpdate();
                            if (rowsAffected > 0) {
                                // Atualiza o produto na lista de produtos
                                for (Produto produto : Settings.getListProduct()) {
                                    if (produto.getIdProduto() == produtoEdit.getIdProduto()) {
                                        int index = Settings.getListProduct().indexOf(produto);
                                        Settings.getListProduct().set(index, produtoEdit);
                                        break;
                                    }
                                }
                                // Atualiza a visualização da tabela
                                tableViewProduct.refresh();

                                // Mostra um alerta de confirmação de edição bem-sucedida
                                Alert alertEditForn = new Alert(Alert.AlertType.INFORMATION);
                                alertEditForn.setTitle("CONFIRMAÇÃO!");
                                alertEditForn.setHeaderText("Edição realizada com sucesso!");
                                alertEditForn.setContentText("Clique no botão para continuar.");
                                alertEditForn.showAndWait();
                                Settings.setFornecedorEdit(null);
                            }
                        } else {
                            // Mostra um alerta de erro se a conexão com a base de dados falhar
                            Alert alertConnError = new Alert(Alert.AlertType.ERROR);
                            alertConnError.setTitle("ERRO!");
                            alertConnError.setHeaderText(null);
                            alertConnError.setContentText("Não foi possível conectar à base de dados.");
                            alertConnError.showAndWait();
                        }
                    } catch (SQLException e) {
                        // Mostra um alerta de erro se ocorrer uma exceção SQL
                        Alert alertError = new Alert(Alert.AlertType.ERROR);
                        alertError.setTitle("ERRO!");
                        alertError.setHeaderText(null);
                        alertError.setContentText("Erro ao atualizar o produto: " + e.getMessage());
                        alertError.showAndWait();
                    }
                }
            }
        }
    }

    public void ProductListar() {
        // Configura as colunas da tabela para associar os atributos do objeto Produto
        tableColumnIdProduct.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("idProduto"));
        tableColumnNameProduct.setCellValueFactory(new PropertyValueFactory<Produto, String>("nomeProduto"));
        tableColumnTypeProduct.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricaoProduto"));
        tableColumnPriceProduct.setCellValueFactory(new PropertyValueFactory<Produto, Double>("precoProduto"));

        Settings.getListProduct().clear();
        // Associação da Obse0rvableList à TableView. A partir daqui, tudo se faz na ObservableList.
        tableViewProduct.setItems(ProdutoDAO.listProduct());

    }

    public void buttonSeeDetailProduct(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("detalheProduto.fxml"));

        Stage seeDetailProduct = new Stage();
        seeDetailProduct.setTitle("Produto - Detalhe do produto");

        // Associação da Scene à Stage
        seeDetailProduct.setScene(new Scene(scene));

        // Abertura da janela seeDetailProduct em modo MODAL, em relação à primaryStage
        seeDetailProduct.initOwner(Settings.getPrimaryStage());
        seeDetailProduct.initModality(Modality.WINDOW_MODAL);

        // Abertura da janela
        seeDetailProduct.show();
    }

    //----------------------------------------------------------------------------------------
    // CLIENTES
    public void verClientes(MouseEvent event) {
        // Obtém os dados do Cliente selecionado na tabela
        Cliente ClientDataVer = (Cliente) tableViewClient.getSelectionModel().getSelectedItem();

        // Mostra o nome do Cliente no campo de texto correspondente
        clientId.setText(String.valueOf(ClientDataVer.getIdCliente()));

        // Mostra o nome do Cliente no campo de texto correspondente
        clientName.setText(String.valueOf(ClientDataVer.getNome()));

        // Mostra o nome do Cliente no campo de texto correspondente
        clientNIF.setText(String.valueOf(ClientDataVer.getNif()));

        // Mostra a morada do Cliente no campo de texto correspondente
        clientAdress.setText(String.valueOf(ClientDataVer.getMorada()));

        // Mostra o número de Cliente no campo de texto correspondente
        clientNumTel.setText(String.valueOf(ClientDataVer.getNumTelemovel()));

        // Mostra o email do Cliente no campo de texto correspondente
        clientEmail.setText(String.valueOf(ClientDataVer.getEmail()));
    }

    public void pesquisarCliente(KeyEvent keyEvent) {
        // Cria um objeto FilteredList para filtrar a lista
        FilteredList<Cliente> filter = new FilteredList<>(Settings.listClient, e -> true);

        // Adiciona um listener ao texto da barra de pesquisa
        clientSearch.textProperty().addListener((Observable, oldValue, newValue) ->{

            filter.setPredicate(predicateClient ->{
                // Verifica se o novo valor da pesquisa é nulo ou vazio
                if(newValue == null && newValue.isEmpty()){
                    return true;
                }
                // Converte o valor da pesquisa para minúsculas
                String pesquisarClient = newValue.toLowerCase();

                // Verifica se o ID do cliente contém o valor da pesquisa
                if (String.valueOf(predicateClient.getIdCliente()).contains(pesquisarClient)){
                    return true;
                    // Verifica se o nome do cliente contém o valor da pesquisa
                }else if (predicateClient.getNome().toLowerCase().contains(pesquisarClient)) {
                    return true;
                    // Verifica se o telefone do cliente contém o valor da pesquisa
                } else if (predicateClient.getNumTelemovel().toLowerCase().contains(pesquisarClient)) {
                    return true;
                }
                // Retorna falso se nenhum critério for atendido
                return false;
            });
        });

        // Cria uma lista ordenada com base no filtro
        SortedList<Cliente> sortList =  new SortedList<>(filter);

        // Atualiza a tabela de clientes com a lista filtrada e ordenada
        sortList.comparatorProperty().bind(tableViewClient.comparatorProperty());
        tableViewClient.setItems(sortList);
    }
    public void buttonAddClient(ActionEvent actionEvent) {
        // Verifica se algum dos campos essenciais está vazio
        if (clientName.getText().isEmpty() || clientNIF.getText().isEmpty() || clientAdress.getText().isEmpty() || clientEmail.getText().isEmpty() || clientNumTel.getText().isEmpty()) {
            // Exibe um alerta de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Por favor, preencha todos os campos obrigatórios.");
            alert.setContentText("Clique no botão para tentar novamente!");
            alert.showAndWait();
        } else {
            try {
                Connection conn = ConexaoBD.openDB();
                if (conn != null) {
                    // Obtém os detalhes do novo Cliente
                    String newName = clientName.getText();
                    String newNIF = clientNIF.getText();
                    String newAdress = clientAdress.getText();
                    String newEmail = clientEmail.getText();
                    String newNumTel = clientNumTel.getText();

                    // Cria um alerta de confirmação para a adição do Cliente
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAR");
                    alert.setHeaderText("Deseja mesmo adicionar este Cliente?");
                    alert.setContentText("Nome: " + newName + "\nNIF: " + newNIF + "\nMorada: " + newAdress + "\nEmail: " + newEmail + "\nNúmero de Telemóvel: " + newNumTel);
                    ButtonType buttonSim = new ButtonType("Sim");
                    ButtonType buttonNao = new ButtonType("Não");
                    alert.getButtonTypes().setAll(buttonSim, buttonNao);

                    // Mostra o alerta de confirmação e aguarda a escolha do utilizador
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == buttonSim) {
                            // Adiciona o novo Cliente à base de dados
                            try {
                                String sql = "INSERT INTO cliente (nome, nif, morada, email, numTelemovel) VALUES (?, ?, ?, ?, ?)";
                                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                stmt.setString(1, newName);
                                stmt.setString(2, newNIF);
                                stmt.setString(3, newAdress);
                                stmt.setString(4, newEmail);
                                stmt.setString(5, newNumTel);
                                stmt.executeUpdate();

                                // Obtém o ID gerado automaticamente
                                int generatedId = -1;
                                ResultSet generatedKeys = stmt.getGeneratedKeys();
                                if (generatedKeys.next()) {
                                    generatedId = generatedKeys.getInt(1);
                                }

                                // Exibe um alerta informativo sobre a adição bem-sucedida
                                Alert alertAddProduct = new Alert(Alert.AlertType.INFORMATION);
                                alertAddProduct.setTitle("INFORMAÇÃO");
                                alertAddProduct.setHeaderText(null);
                                alertAddProduct.setContentText("Cliente inserido com sucesso!");
                                alertAddProduct.showAndWait();

                                // Adiciona o Cliente à tabela
                                Cliente cliente = new Cliente(generatedId, newName, newNIF, newAdress, newEmail, newNumTel);
                                tableViewClient.getItems().add(cliente);

                                // Limpa os campos após a adição bem-sucedida
                                clientName.clear();
                                clientNIF.clear();
                                clientAdress.clear();
                                clientEmail.clear();
                                clientNumTel.clear();
                            } catch (SQLException e) {
                                // Exibe um alerta de erro se ocorrer um erro ao adicionar o cliente a base de dados
                                Alert alertError = new Alert(Alert.AlertType.ERROR);
                                alertError.setTitle("ERRO");
                                alertError.setHeaderText(null);
                                alertError.setContentText("Erro ao adicionar o Cliente: " + e.getMessage());
                                alertError.showAndWait();
                            }
                        }
                    });
                } else {
                    // Exibe um alerta de erro se não for possível abrir a conexão com o banco de dados
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setHeaderText(null);
                    alert.setContentText("Não foi possível ligar à base de dados.");
                    alert.showAndWait();
                }
            } finally {
                ConexaoBD.closeDB(); // Fecha a conexão com o banco de dados após o uso
            }
        }
    }

    public void buttonRemoveClient(ActionEvent actionEvent) {
        // Verifica se algum dos campos obrigatórios está vazio
        if (clientName.getText().isEmpty() || clientNIF.getText().isEmpty() || clientAdress.getText().isEmpty() || clientEmail.getText().isEmpty() || clientNumTel.getText().isEmpty()) {
            // Mostra um alerta de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Nenhum Cliente selecionado!");
            alert.setContentText("Selecione um fornecedor para remover.");
            alert.showAndWait();
        } else {
            // Obtém a conexão com a base de dados
            Connection conn = ConexaoBD.openDB();
            if (conn != null) {
                // Obtém os valores dos campos de texto
                String newName = clientName.getText();
                String newNIF = clientNIF.getText();
                String newAdress = clientAdress.getText();
                String newEmail = clientEmail.getText();
                String newNumTel = clientNumTel.getText();

                // Mostra um alerta de confirmação perguntando ao utilizador se deseja realmente remover o cliente
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMAR");
                alert.setHeaderText("Deseja mesmo remover este cliente?");
                alert.setContentText("Nome: " + newName + "\nNIF: " + newNIF + "\nMorada: " + newAdress + "\nEmail:" + newEmail + "\nNúmero de Telemovel: " + newNumTel + "\nDeseja mesmo remover?");
                ButtonType buttonSim = new ButtonType("Sim");
                ButtonType buttonNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(buttonSim, buttonNao);

                // Aguarda a escolha do utilizador
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.isPresent() && choose.get() == buttonSim) {
                    // Obtém o ID do cliente a partir do campo de texto
                    int id = Integer.parseInt(clientId.getText());
                    // Remove o cliente do banco de dados
                    ClienteDAO.removerClient(id);
                    // Obtém o cliente selecionado na tabela
                    Cliente clienteRemovido = (Cliente) tableViewClient.getSelectionModel().getSelectedItem();
                    // Remove o cliente da tabela de visualização
                    tableViewClient.getItems().remove(clienteRemovido);

                    // Limpeza dos campos após a remoção
                    clientId.clear();
                    clientName.clear();
                    clientNIF.clear();
                    clientAdress.clear();
                    clientEmail.clear();
                    clientNumTel.clear();

                    // Mostrar alerta de remoção bem-sucedida
                    Alert alertRmClient = new Alert(Alert.AlertType.INFORMATION);
                    alertRmClient.setTitle("INFORMAÇÃO");
                    alertRmClient.setHeaderText(null);
                    alertRmClient.setContentText("Cliente removido com sucesso!");
                    alertRmClient.showAndWait();
                }
            } else {
                // Mostra um alerta de erro se a conexão com a base de dados falhar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível conectar à base de dados.");
                alert.showAndWait();
            }
        }
    }

    public void buttonEditClient(ActionEvent actionEvent) {
        // Verifica se algum dos campos obrigatórios está vazio
        if (clientName.getText().isEmpty() || clientNIF.getText().isEmpty() || clientAdress.getText().isEmpty() || clientEmail.getText().isEmpty() || clientNumTel.getText().isEmpty()) {
            // Mostra um alerta de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setHeaderText("Por favor,coloque todos os dados nos respetivos campos");
            alert.setContentText("Clique no botão para tentar novamente!");
            alert.showAndWait();
        } else {
            // Obtém o ID do cliente a partir do campo de textov
            int newClientId = Integer.parseInt(clientId.getText());
            Cliente clienteEdit = null;

            // Procura o cliente na lista de clientes
            for (Cliente c : Settings.getListClient()) {
                if (c.getIdCliente() == newClientId) {
                    clienteEdit = c;
                    break;
                }
            }
            // Se o cliente foi encontrado
            if (clienteEdit != null) {
                // Atualiza os dados do cliente com os valores dos campos de texto
                clienteEdit.setNome(clientName.getText());
                clienteEdit.setNif(clientNIF.getText());
                clienteEdit.setMorada(clientAdress.getText());
                clienteEdit.setNumTelemovel(clientNumTel.getText());
                clienteEdit.setEmail(clientEmail.getText());

                // Mostra um alerta de confirmação perguntando ao utilizador se deseja realmente editar o cliente
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Editar Cliente");
                alert.setHeaderText("Deseja realmente editar?");
                alert.setContentText("Clique no botão para continuar!");
                ButtonType buttonSim = new ButtonType("Sim");
                ButtonType buttonNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(buttonSim, buttonNao);
                // Aguarda a escolha do utilizado
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.isPresent() && choose.get() == buttonSim) {
                    Connection conn = null;
                    try {
                        // Obtém a conexão com a base de dados
                        conn = ConexaoBD.openDB();
                        if (conn != null) {
                            // Cria o SQL de atualização
                            String sql = "UPDATE cliente SET nome = ?, nif = ?, morada = ?, numTelemovel = ?, email = ? WHERE idCliente = ?";
                            // Define os parâmetros do SQL
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, clientName.getText());
                            stmt.setString(2, clientNIF.getText());
                            stmt.setString(3, clientAdress.getText());
                            stmt.setString(4, clientNumTel.getText());
                            stmt.setString(5, clientEmail.getText());
                            stmt.setInt(6, newClientId);

                            // Executa a atualização e verifica se alguma linha foi afetada
                            int rowsAffected = stmt.executeUpdate();
                            if (rowsAffected > 0) {
                                // Atualiza a lista de clientes com os novos dados
                                for (Cliente cliente : Settings.getListClient()) {
                                    if (cliente.getIdCliente() == clienteEdit.getIdCliente()) {
                                        int index = Settings.getListClient().indexOf(cliente);
                                        Settings.getListClient().set(index, clienteEdit);
                                        break;
                                    }
                                }
                                tableViewClient.refresh();

                                // Mostra um alerta de confirmação de edição bem-sucedida
                                Alert alertEditClient = new Alert(Alert.AlertType.INFORMATION);
                                alertEditClient.setTitle("CONFIRMAÇÃO!");
                                alertEditClient.setHeaderText("Edição realizada com sucesso!");
                                alertEditClient.setContentText("Clique no botão para continuar.");
                                alertEditClient.showAndWait();
                                Settings.setClientEdit(null);
                            }
                        } else {
                            // Mostra um alerta de erro se a conexão com a base de dados falhar
                            Alert alertConnError = new Alert(Alert.AlertType.ERROR);
                            alertConnError.setTitle("ERRO!");
                            alertConnError.setHeaderText(null);
                            alertConnError.setContentText("Não foi possível conectar à base de dados.");
                            alertConnError.showAndWait();
                        }
                    } catch (SQLException e) {
                        // Mostra um alerta de erro se ocorrer um problema ao atualizar o cliente
                        Alert alertError = new Alert(Alert.AlertType.ERROR);
                        alertError.setTitle("ERRO!");
                        alertError.setHeaderText(null);
                        alertError.setContentText("Erro ao atualizar o cliente: " + e.getMessage());
                        alertError.showAndWait();
                    }
                }
            }
        }
    }

    // Método para mostrar os dados do clientes numa tabela
    public void clienteListar() {
        // Configura as colunas da tabela para associar os atributos do objeto Fornecedor
        tableColumnIdClient.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        tableColumnNameClient.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        tableColumnNifClient.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nif"));
        tableColumnAdressClient.setCellValueFactory(new PropertyValueFactory<Cliente, String>("morada"));
        tableColumnEmailCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
        tableColumnNumTelClient.setCellValueFactory(new PropertyValueFactory<Cliente, String>("numTelemovel"));


        tableViewClient.setItems(ClienteDAO.listCliente());
    }


    //----------------------------------------------------------------------------------------
    // FORNECEDOR - FEITO TUDO
    public void verFornecedor(MouseEvent event) {
        // Obtém os dados do Fornecedor selecionado na tabela
        Fornecedor FornDataVer = (Fornecedor) tableViewFornecedor.getSelectionModel().getSelectedItem();

        // Mostra o nome do Fornecedor no campo de texto correspondente
        fornecedorId.setText(String.valueOf(FornDataVer.getIdFornecedor()));

        // Define o nome do Fornecedor no campo de texto correspondente
        fornecedorName.setText(String.valueOf(FornDataVer.getNome()));

        // Define o nome do Fornecedor no campo de texto correspondente
        fornecedorNIF.setText(String.valueOf(FornDataVer.getNif()));

        // Define a morada do Fornecedor no campo de texto correspondente
        fornecedorAdress.setText(String.valueOf(FornDataVer.getMorada()));

        // Define o número de telefone do Fornecedor no campo de texto correspondente
        fornecedorNumTel.setText(String.valueOf(FornDataVer.getNumTelemovel()));

        // Define o email do Fornecedor no campo de texto correspondente
        fornecedorEmail.setText(String.valueOf(FornDataVer.getEmail()));
    }

    public void pesquisarFornecedor(KeyEvent keyEvent) {
        // Cria um objeto FilteredList para filtrar a lista
        FilteredList<Fornecedor> filter = new FilteredList<>(Settings.listForn, e -> true);

        // Adiciona um listener ao texto da barra de pesquisa
        FornecedorSearch.textProperty().addListener((Observable, oldValue, newValue) ->{

            filter.setPredicate(predicateClient ->{
                // Verifica se o novo valor da pesquisa é nulo ou vazio
                if(newValue == null && newValue.isEmpty()){
                    return true;
                }
                // Converte o valor da pesquisa para minúsculas
                String pesquisarForn = newValue.toLowerCase();

                // Verifica se o ID do Fornecedor contém o valor da pesquisa
                if (String.valueOf(predicateClient.getIdFornecedor()).contains(pesquisarForn)){
                    return true;
                    // Verifica se o nome do Fornecedor contém o valor da pesquisa
                }else if (predicateClient.getNome().toLowerCase().contains(pesquisarForn)) {
                    return true;
                    // Verifica se o telefone do Fornecedor contém o valor da pesquisa
                } else if (predicateClient.getNumTelemovel().toLowerCase().contains(pesquisarForn)) {
                    return true;
                }
                // Retorna falso se nenhum critério for atendido
                return false;
            });
        });

        // Cria uma lista ordenada com base no filtro
        SortedList<Fornecedor> sortList =  new SortedList<>(filter);

        // Atualiza a tabela de clientes com a lista filtrada e ordenada
        sortList.comparatorProperty().bind(tableViewFornecedor.comparatorProperty());
        tableViewFornecedor.setItems(sortList);
    }

    public void buttonFornAdd(ActionEvent actionEvent) {
        // Verifica se algum dos campos essenciais está vazio
        if (fornecedorName.getText().isEmpty() || fornecedorNIF.getText().isEmpty() || fornecedorAdress.getText().isEmpty() || fornecedorEmail.getText().isEmpty() || fornecedorNumTel.getText().isEmpty()) {
            // Exibe um alerta de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Por favor, preencha todos os campos obrigatórios.");
            alert.setContentText("Clique no botão para tentar novamente!");
            alert.showAndWait();
        } else {
            try {
                Connection conn = ConexaoBD.openDB();
                if (conn != null) {
                    // Obtém os detalhes do novo fornecedor
                    String newName = fornecedorName.getText();
                    String newNIF = fornecedorNIF.getText();
                    String newAdress = fornecedorAdress.getText();
                    String newEmail = fornecedorEmail.getText();
                    String newNumTel = fornecedorNumTel.getText();

                    // Cria um alerta de confirmação para a adição do fornecedor
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAR");
                    alert.setHeaderText("Deseja mesmo adicionar este fornecedor?");
                    alert.setContentText("Nome: " + newName + "\nNIF: " + newNIF + "\nMorada: " + newAdress + "\nEmail: " + newEmail + "\nNúmero de Telemóvel: " + newNumTel + "\nDeseja mesmo adicionar?");
                    ButtonType buttonSim = new ButtonType("Sim");
                    ButtonType buttonNao = new ButtonType("Não");
                    alert.getButtonTypes().setAll(buttonSim, buttonNao);

                    // Mostra o alerta de confirmação e aguarda a escolha do utilizador
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == buttonSim) {
                            // Adiciona o novo fornecedor à base de dados
                            try {
                                String sql = "INSERT INTO fornecedor (nome, nif, morada, email, numTelemovel) VALUES (?, ?, ?, ?, ?)";
                                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                stmt.setString(1, newName);
                                stmt.setString(2, newNIF);
                                stmt.setString(3, newAdress);
                                stmt.setString(4, newEmail);
                                stmt.setString(5, newNumTel);
                                stmt.executeUpdate();

                                // Obtém o ID gerado automaticamente
                                int generatedId = -1;
                                ResultSet generatedKeys = stmt.getGeneratedKeys();
                                if (generatedKeys.next()) {
                                    generatedId = generatedKeys.getInt(1);
                                }

                                // Exibe um alerta informativo sobre a adição bem-sucedida
                                Alert alertAddProduct = new Alert(Alert.AlertType.INFORMATION);
                                alertAddProduct.setTitle("INFORMAÇÃO");
                                alertAddProduct.setHeaderText(null);
                                alertAddProduct.setContentText("Fornecedor inserido com sucesso!");
                                alertAddProduct.showAndWait();

                                // Adiciona o fornecedor à tabela
                                Fornecedor fornecedor = new Fornecedor(generatedId, newName, newNIF, newAdress, newEmail, newNumTel);
                                tableViewFornecedor.getItems().add(fornecedor);

                                // Limpa os campos após a adição bem-sucedida
                                fornecedorName.clear();
                                fornecedorNIF.clear();
                                fornecedorAdress.clear();
                                fornecedorEmail.clear();
                                fornecedorNumTel.clear();
                            } catch (SQLException e) {
                                // Exibe um alerta de erro se ocorrer um erro ao adicionar o fornecedor ao banco de dados
                                Alert alertError = new Alert(Alert.AlertType.ERROR);
                                alertError.setTitle("ERRO");
                                alertError.setHeaderText(null);
                                alertError.setContentText("Erro ao adicionar o fornecedor: " + e.getMessage());
                                alertError.showAndWait();
                            }
                        }
                    });
                } else {
                    // Exibe um alerta de erro se não for possível abrir a conexão com o banco de dados
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setHeaderText(null);
                    alert.setContentText("Não foi possível ligar à base de dados.");
                    alert.showAndWait();
                }
            } finally {
                ConexaoBD.closeDB(); // Fecha a conexão com o banco de dados após o uso
            }
        }
    }

    public void buttonFornRemove(ActionEvent actionEvent) throws SQLException {
        // Verifica se algum dos campos obrigatórios está vazio
        if (fornecedorName.getText().isEmpty() || fornecedorNIF.getText().isEmpty() || fornecedorAdress.getText().isEmpty() || fornecedorEmail.getText().isEmpty() || fornecedorNumTel.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Nenhum fornecedor selecionado!");
            alert.setContentText("Selecione um fornecedor para remover.");
            alert.showAndWait();
        } else {
            // Obtenha a conexão com o banco de dados
            Connection conn = ConexaoBD.openDB();
            if (conn != null) {
                // Obtém os dados do fornecedor dos campos de texto
                String newName = fornecedorName.getText();
                String newNIF = fornecedorNIF.getText();
                String newAdress = fornecedorAdress.getText();
                String newEmail = fornecedorEmail.getText();
                String newNumTel = fornecedorNumTel.getText();

                // Mostra um alerta a questionar se deseja realmente remover o fornecedor
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMAR");
                alert.setHeaderText("Deseja mesmo remover este fornecedor?");
                alert.setContentText("Nome: " + newName + "\nNIF: " + newNIF + "\nMorada: " + newAdress + "\nEmail:" + newEmail + "\nNúmero de Telemovel: " + newNumTel + "\nDeseja mesmo remover?");
                ButtonType buttonSim = new ButtonType("Sim");
                ButtonType buttonNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(buttonSim, buttonNao);

                // Aguarda a escolha do utilizador
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.isPresent() && choose.get() == buttonSim) {
                    // Obtém o ID do fornecedor a partir do campo de texto
                    int id = Integer.parseInt(fornecedorId.getText());
                    // Remove o fornecedor da base de dados
                    FornecedorDAO.removerForn(id);
                    //System.out.println(Settings.getListForn().size());
                    // Remove o fornecedor da tabela de visualização
                    Fornecedor fornecedorRemovido = (Fornecedor) tableViewFornecedor.getSelectionModel().getSelectedItem();
                    tableViewFornecedor.getItems().remove(fornecedorRemovido);

                    // Limpeza dos campos após a remoção
                    fornecedorId.clear();
                    fornecedorName.clear();
                    fornecedorNIF.clear();
                    fornecedorAdress.clear();
                    fornecedorEmail.clear();
                    fornecedorNumTel.clear();

                    // Mostrar alerta de remoção bem-sucedida
                    Alert alertRmFornecedor = new Alert(Alert.AlertType.INFORMATION);
                    alertRmFornecedor.setTitle("INFORMAÇÃO");
                    alertRmFornecedor.setHeaderText(null);
                    alertRmFornecedor.setContentText("Fornecedor removido com sucesso!");
                    alertRmFornecedor.showAndWait();
                }
            } else {
                // Mostra um alerta de erro se a conexão com a base de dados falhar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível conectar à base de dados.");
                alert.showAndWait();
            }
        }
    }

    public void buttonFornEdit(ActionEvent actionEvent) {
        // Verifica se algum dos campos obrigatórios está vazio
        if (fornecedorName.getText().isEmpty() || fornecedorNIF.getText().isEmpty() || fornecedorAdress.getText().isEmpty() || fornecedorEmail.getText().isEmpty() || fornecedorNumTel.getText().isEmpty()) {
            // Cria e mostra uma mensagem de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setHeaderText("Por favor,coloque todos os dados nos respetivos campos");
            alert.setContentText("Clique no botão para tentar novamente!");
            alert.showAndWait();
        } else {
            // Obtém o ID do fornecedor a partir do campo de texto
            int newFornId = Integer.parseInt(fornecedorId.getText());
            Fornecedor fornecedorEdit = null;

            // Procura o fornecedor na lista de fornecedores
            for (Fornecedor f : Settings.getListForn()) {
                if (f.getIdFornecedor() == newFornId) {
                    fornecedorEdit = f;
                    break;
                }
            }
            // Se o fornecedor foi encontrado na lista
            if (fornecedorEdit != null) {
                // Atualiza os dados do fornecedor com os valores dos campos de texto
                fornecedorEdit.setNome(fornecedorName.getText());
                fornecedorEdit.setNif(fornecedorNIF.getText());
                fornecedorEdit.setMorada(fornecedorAdress.getText());
                fornecedorEdit.setNumTelemovel(fornecedorNumTel.getText());
                fornecedorEdit.setEmail(fornecedorEmail.getText());

                // Cria um alerta de confirmação para confirmar a edição
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Editar Fornecedor");
                alert.setHeaderText("Deseja realmente editar?");
                alert.setContentText("Clique no botão para continuar!");
                ButtonType buttonSim = new ButtonType("Sim");
                ButtonType buttonNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(buttonSim, buttonNao);

                // Mostra o alerta e espera pela resposta do utilizador
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.isPresent() && choose.get() == buttonSim) {
                    Connection conn = null;
                    try {
                        // Tenta abrir a conexão com a base de dados
                        conn = ConexaoBD.openDB();
                        if (conn != null) {
                            // Prepara a instrução SQL para atualizar os dados do fornecedor
                            String sql = "UPDATE fornecedor SET nome = ?, nif = ?, morada = ?, numTelemovel = ?, email = ? WHERE idFornecedor = ?";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, fornecedorName.getText());
                            stmt.setString(2, fornecedorNIF.getText());
                            stmt.setString(3, fornecedorAdress.getText());
                            stmt.setString(4, fornecedorNumTel.getText());
                            stmt.setString(5, fornecedorEmail.getText());
                            stmt.setInt(6, newFornId);

                            // Executa a atualização e verifica se foi bem-sucedida
                            int rowsAffected = stmt.executeUpdate();
                            if (rowsAffected > 0) {
                                // Atualiza a lista de fornecedores com os novos dados
                                for (Fornecedor fornecedor : Settings.getListForn()) {
                                    if (fornecedor.getIdFornecedor() == fornecedorEdit.getIdFornecedor()) {
                                        int index = Settings.getListForn().indexOf(fornecedor);
                                        Settings.getListForn().set(index, fornecedorEdit);
                                        break;
                                    }
                                }
                                // Atualiza a tabela de fornecedores
                                tableViewFornecedor.refresh();

                                // Cria e mostra uma mensagem de confirmação
                                Alert alertEditForn = new Alert(Alert.AlertType.INFORMATION);
                                alertEditForn.setTitle("CONFIRMAÇÃO!");
                                alertEditForn.setHeaderText("Edição realizada com sucesso!");
                                alertEditForn.setContentText("Clique no botão para continuar.");
                                alertEditForn.showAndWait();
                                // Define o fornecedor editado como null
                                Settings.setFornecedorEdit(null);
                            }
                        } else {
                            // Cria e mostra uma mensagem de erro se não for possível conectar à base de dados
                            Alert alertConnError = new Alert(Alert.AlertType.ERROR);
                            alertConnError.setTitle("ERRO!");
                            alertConnError.setHeaderText(null);
                            alertConnError.setContentText("Não foi possível conectar à base de dados.");
                            alertConnError.showAndWait();
                        }
                    } catch (SQLException e) {
                        // Cria e mostra uma mensagem de erro se ocorrer uma exceção SQL
                        Alert alertError = new Alert(Alert.AlertType.ERROR);
                        alertError.setTitle("ERRO!");
                        alertError.setHeaderText(null);
                        alertError.setContentText("Erro ao atualizar o fornecedor: " + e.getMessage());
                        alertError.showAndWait();
                    }
                }
            }
        }
    }

    public void buttonDetailForn(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("detalheFornecedor.fxml"));

        Stage seeDetailForn = new Stage();
        seeDetailForn.setTitle("Fornecedor - Detalhe do Fornecedor");

        // Associação da Scene à Stage
        seeDetailForn.setScene(new Scene(scene));

        // Abertura da janela seeDetailProduct em modo MODAL, em relação à primaryStage
        seeDetailForn.initOwner(Settings.getPrimaryStage());
        seeDetailForn.initModality(Modality.WINDOW_MODAL);

        // Abertura da janela
        seeDetailForn.show();
    }

    public void fornecedorListar() {
        // Configura as colunas da tabela para associar os atributos do objeto Fornecedor
        tableColumnIdForn.setCellValueFactory(new PropertyValueFactory<Fornecedor, Integer>("idFornecedor"));
        tableColumnNameForn.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nome"));
        tableColumnNifForn.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nif"));
        tableColumnAdressForn.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("morada"));
        tableColumnEmailForn.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("email"));
        tableColumnNumTelForn.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("numTelemovel"));

        tableViewFornecedor.setItems(FornecedorDAO.listFornecedor());
    }

}
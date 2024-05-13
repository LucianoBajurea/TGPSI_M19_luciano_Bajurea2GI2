package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.tgpsi_m19_luciano_bajurea.ConexaoBD.*;

public class PrincipalController implements Initializable {

    public Button btnSeeDetailProduct;
    public Button btnFornecedor;
    public TextField clientSearch;
    public TextField clientNIF;
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
    }

    public void buttonOnClient(ActionEvent actionEvent) {
        // Oculta os painéis relacionados a Clientes, Vendedores e Informações "Acerca de..."
        // Torna visível o painel relacionado ao Produto
        AnchorPaneClient.setVisible(true);
        AnchorPaneFornecedor.setVisible(false);
        AnchorPaneAbout.setVisible(false);
        AnchorPaneProduct.setVisible(false);
    }

    public void buttonFornecedor(ActionEvent actionEvent) {
        // Oculta os painéis relacionados a Clientes, Informações "Acerca de..." e Produtos
        // Torna visível o painel relacionado o Vendedor
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
        // Oculta os painéis relacionados a Vendedores, Informações "Acerca de..." e Produtos
        // Torna visível o painel relacionado a Clientes
        AnchorPaneClient.setVisible(false);
        AnchorPaneFornecedor.setVisible(false);
        AnchorPaneAbout.setVisible(true);
        AnchorPaneProduct.setVisible(false);

    }

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

    private String[] typeProductCombo = {"Roupa", "Rélogios", "Objetos automovel"};
    public void InventoryTypeProduct(){
        List<String> ListP = new ArrayList<>();
        for(String data : typeProductCombo){
            ListP.add(data);
        }
        ObservableList listData =FXCollections.observableArrayList(ListP);

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

    public void buttonAdd(ActionEvent actionEvent) {
        // Verifica se algum dos campos essenciais está vazio
        if (productId.getText().isEmpty() || productName.getText().isEmpty() || productCategoria.getSelectionModel().getSelectedItem() == null || productPrice.getText().isEmpty()) {
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
                    // Se tudo estiver correto, obtém os detalhes do novo produto
                    int newId = Integer.parseInt(productId.getText());
                    String newName = productName.getText();
                    double newPrice = Double.parseDouble(productPrice.getText());
                    String newCategoria = String.valueOf(productCategoria.getSelectionModel().getSelectedItem());

                    // Cria um alerta de confirmação para a adição do produto
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAR");
                    alert.setHeaderText("Deseja mesmo adicionar este produto?");
                    alert.setContentText("Nome: " + newName + "\nPreço: " + newPrice + "\nCategoria: "+ newCategoria + "\nDeseja mesmo adicionar?");
                    ButtonType buttonSim = new ButtonType("Sim");
                    ButtonType buttonNao = new ButtonType("Não");
                    alert.getButtonTypes().setAll(buttonSim, buttonNao);

                    // Exibe o alerta de confirmação e aguarda a escolha do utilizador
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == buttonSim) {
                            // Tenta adicionar o novo produto ao banco de dados
                            try {
                                String sql = "INSERT INTO produto (idProduto, nomeProduto, precoProduto, descricaoProduto) VALUES (?, ?, ?, ?)";
                                PreparedStatement stmt = conn.prepareStatement(sql);
                                stmt.setInt(1, newId);
                                stmt.setString(2, newName);
                                stmt.setDouble(3, newPrice);
                                stmt.setString(4, newCategoria);
                                stmt.executeUpdate();

                                // Exibe um alerta informativo sobre a adição bem-sucedida
                                Alert alertAddProduct = new Alert(Alert.AlertType.INFORMATION);
                                alertAddProduct.setTitle("INFORMAÇÃO");
                                alertAddProduct.setHeaderText(null);
                                alertAddProduct.setContentText("Produto inserido com sucesso!");
                                alertAddProduct.showAndWait();

                                // Adiciona o fornecedor à tabela
                                Produto produto = new Produto(newId, newName, newPrice, newCategoria);
                                tableViewProduct.getItems().add(produto);

                                // Limpa os campos após a adição bem-sucedida
                                productId.clear();
                                productName.clear();
                                productPrice.clear();
                                productCategoria.getSelectionModel().clearSelection();

                            } catch (SQLException e) {
                                // Exibe um alerta de erro se ocorrer um erro ao adicionar o produto ao banco de dados
                                Alert alertError = new Alert(Alert.AlertType.ERROR);
                                alertError.setTitle("ERRO");
                                alertError.setHeaderText(null);
                                alertError.setContentText("Erro ao adicionar o produto: " + e.getMessage());
                                alertError.showAndWait();
                            }
                        }
                    });
                } else {
                    // Exibe um alerta de erro se não for possível abrir a conexão com o banco de dados
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setHeaderText(null);
                    alert.setContentText("Não foi possível conectar ao banco de dados.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                // Exibe um alerta de erro se houver um problema com a conversão de tipos de dados
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText(null);
                alert.setContentText("Certifique-se de que o ID é um número e que o preço está em um formato válido.");
                alert.showAndWait();
            } finally {
                ConexaoBD.closeDB(); // Fecha a conexão com o banco de dados após o uso
            }
        }
    }

    public void buttonRemove(ActionEvent actionEvent) throws Exception {

    }

    public void buttonEdit(ActionEvent actionEvent) {

    }

    public void typeProduct() {
        // Configura as colunas da tabela para associar os atributos do objeto Produto
        tableColumnIdProduct.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("idProduto"));
        tableColumnNameProduct.setCellValueFactory(new PropertyValueFactory<Produto, String>("NomeProduto"));
        tableColumnTypeProduct.setCellValueFactory(new PropertyValueFactory<Produto, String>("tipoProduto"));
        tableColumnPriceProduct.setCellValueFactory(new PropertyValueFactory<Produto, Double>("preco"));


        // Associação da Obse0rvableList à TableView. A partir daqui, tudo se faz na ObservableList.
        tableViewProduct.setItems(Settings.getListProduct());

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
        // Obtém os dados do Fornecedor selecionado na tabela
        Cliente ClientDataVer = (Cliente) tableViewClient.getSelectionModel().getSelectedItem();

        // Define o ID do Fornecedor no campo de texto correspondente
        clientId.setText(String.valueOf(ClientDataVer.getIdCliente()));

        // Define o nome do Fornecedor no campo de texto correspondente
        clientName.setText(String.valueOf(ClientDataVer.getNome()));

        // Define o nome do Fornecedor no campo de texto correspondente
        clientNIF.setText(String.valueOf(ClientDataVer.getNif()));

        // Define a morada do Fornecedor no campo de texto correspondente
        clientAdress.setText(String.valueOf(ClientDataVer.getMorada()));

        // Define o número de telefone do Fornecedor no campo de texto correspondente
        clientNumTel.setText(String.valueOf(ClientDataVer.getNumTelemovel()));

        // Define o email do Fornecedor no campo de texto correspondente
        clientEmail.setText(String.valueOf(ClientDataVer.getEmail()));
    }

    public void buttonAddClient(ActionEvent actionEvent) {
        // Verifica se algum dos campos essenciais está vazio
        if (clientId.getText().isEmpty() || clientName.getText().isEmpty() || clientNIF.getText().isEmpty() || clientAdress.getText().isEmpty() || clientEmail.getText().isEmpty() || clientNumTel.getText().isEmpty()) {
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
                    // Se tudo estiver correto, obtém os detalhes do novo produto
                    int newId = Integer.parseInt(clientId.getText());
                    String newName = clientName.getText();
                    String newNIF = clientNIF.getText();
                    String newAdress = clientAdress.getText();
                    String newEmail = clientEmail.getText();
                    String newNumTel = clientNumTel.getText();

                    // Cria um alerta de confirmação para a adição do produto
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAR");
                    alert.setHeaderText("Deseja mesmo adicionar este produto?");
                    alert.setContentText("Nome: " + newName + "\nNIF: " + newNIF + "\nMorada: " + newAdress + "\nEmail:" + newEmail + "\nNúmero de Telemovel: " + newNumTel + "\nDeseja mesmo adicionar?");
                    ButtonType buttonSim = new ButtonType("Sim");
                    ButtonType buttonNao = new ButtonType("Não");
                    alert.getButtonTypes().setAll(buttonSim, buttonNao);

                    // Mostra o alerta de confirmação e aguarda a escolha do utilizador
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == buttonSim) {
                            // Adiciona o novo produto a base de dados
                            try {
                                String sql = "INSERT INTO cliente (idCliente, nome, nif, morada, email, numTelemovel) VALUES (?, ?, ?, ?, ?, ?)";
                                PreparedStatement stmt = conn.prepareStatement(sql);
                                stmt.setInt(1, newId);
                                stmt.setString(2, newName);
                                stmt.setString(3, newNIF);
                                stmt.setString(4, newAdress);
                                stmt.setString(5, newEmail);
                                stmt.setString(6, newNumTel);
                                stmt.executeUpdate();

                                // Exibe um alerta informativo sobre a adição bem-sucedida
                                Alert alertAddProduct = new Alert(Alert.AlertType.INFORMATION);
                                alertAddProduct.setTitle("INFORMAÇÃO");
                                alertAddProduct.setHeaderText(null);
                                alertAddProduct.setContentText("Cliente inserido com sucesso!");
                                alertAddProduct.showAndWait();

                                // Adiciona o Cliente à tabela
                                Cliente cliente = new Cliente(newId, newName, newNIF, newAdress, newEmail, newNumTel);
                                tableViewClient.getItems().add(cliente);

                                // Limpa os campos após a adição bem-sucedida
                                clientId.clear();
                                clientName.clear();
                                clientNIF.clear();
                                clientAdress.clear();
                                clientEmail.clear();
                                clientNumTel.clear();
                            } catch (SQLException e) {
                                // Exibe um alerta de erro se ocorrer um erro ao adicionar o produto ao banco de dados
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
                    alert.setContentText("Não foi possível ligar a base de dados.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                // Exibe um alerta de erro se houver um problema com a conversão de tipos de dados
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText(null);
                alert.setContentText("Certifique-se de que o ID é um número e que o preço está em um formato válido.");
                alert.showAndWait();
            } finally {
                ConexaoBD.closeDB(); // Fecha a conexão com o banco de dados após o uso
            }
        }
    }

    public void buttonRemoveClient(ActionEvent actionEvent) {
    }

    public void buttonEditClient(ActionEvent actionEvent) {
    }

    public void clienteListar() {
        // Configura as colunas da tabela para associar os atributos do objeto Fornecedor
        tableColumnIdClient.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        tableColumnNameClient.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        tableColumnNifClient.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nif"));
        tableColumnAdressClient.setCellValueFactory(new PropertyValueFactory<Cliente, String>("morada"));
        tableColumnEmailCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
        tableColumnNumTelClient.setCellValueFactory(new PropertyValueFactory<Cliente, String>("numTelemovel"));

        tableViewClient.setItems(Settings.getListClient());
    }

    //----------------------------------------------------------------------------------------
    // FORNECEDOR
    public void verFornecedor(MouseEvent event) {
        // Obtém os dados do Fornecedor selecionado na tabela
        Fornecedor FornDataVer = (Fornecedor) tableViewFornecedor.getSelectionModel().getSelectedItem();

        // Define o ID do Fornecedor no campo de texto correspondente
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
    }

    public void buttonFornAdd(ActionEvent actionEvent) {
        // Verifica se algum dos campos essenciais está vazio
        if (fornecedorId.getText().isEmpty() || fornecedorName.getText().isEmpty() || fornecedorNIF.getText().isEmpty() || fornecedorAdress.getText().isEmpty() || fornecedorEmail.getText().isEmpty() || fornecedorNumTel.getText().isEmpty()) {
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
                    // Se tudo estiver correto, obtém os detalhes do novo produto
                    int newId = Integer.parseInt(fornecedorId.getText());
                    String newName = fornecedorName.getText();
                    String newNIF = fornecedorNIF.getText();
                    String newAdress = fornecedorAdress.getText();
                    String newEmail = fornecedorEmail.getText();
                    String newNumTel = fornecedorNumTel.getText();

                    // Cria um alerta de confirmação para a adição do produto
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAR");
                    alert.setHeaderText("Deseja mesmo adicionar este produto?");
                    alert.setContentText("Nome: " + newName + "\nNIF: " + newNIF + "\nMorada: " + newAdress + "\nEmail:" + newEmail + "\nNúmero de Telemovel: " + newNumTel + "\nDeseja mesmo adicionar?");
                    ButtonType buttonSim = new ButtonType("Sim");
                    ButtonType buttonNao = new ButtonType("Não");
                    alert.getButtonTypes().setAll(buttonSim, buttonNao);

                    // Mostra o alerta de confirmação e aguarda a escolha do utilizador
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == buttonSim) {
                            // Adiciona o novo produto a base de dados
                            try {
                                String sql = "INSERT INTO fornecedor (idFornecedor, nome, nif, morada, email, numTelemovel) VALUES (?, ?, ?, ?, ?, ?)";
                                PreparedStatement stmt = conn.prepareStatement(sql);
                                stmt.setInt(1, newId);
                                stmt.setString(2, newName);
                                stmt.setString(3, newNIF);
                                stmt.setString(4, newAdress);
                                stmt.setString(5, newEmail);
                                stmt.setString(6, newNumTel);
                                stmt.executeUpdate();

                                // Exibe um alerta informativo sobre a adição bem-sucedida
                                Alert alertAddProduct = new Alert(Alert.AlertType.INFORMATION);
                                alertAddProduct.setTitle("INFORMAÇÃO");
                                alertAddProduct.setHeaderText(null);
                                alertAddProduct.setContentText("Fornecedor inserido com sucesso!");
                                alertAddProduct.showAndWait();

                                // Adiciona o fornecedor à tabela
                                Fornecedor fornecedor = new Fornecedor(newId, newName, newNIF, newAdress, newEmail, newNumTel);
                                tableViewFornecedor.getItems().add(fornecedor);

                                // Limpa os campos após a adição bem-sucedida
                                fornecedorId.clear();
                                fornecedorName.clear();
                                fornecedorNIF.clear();
                                fornecedorAdress.clear();
                                fornecedorEmail.clear();
                                fornecedorNumTel.clear();
                            } catch (SQLException e) {
                                // Exibe um alerta de erro se ocorrer um erro ao adicionar o produto ao banco de dados
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
                    alert.setContentText("Não foi possível ligar a base de dados.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                // Exibe um alerta de erro se houver um problema com a conversão de tipos de dados
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText(null);
                alert.setContentText("Certifique-se de que o ID é um número e que o preço está em um formato válido.");
                alert.showAndWait();
            } finally {
                ConexaoBD.closeDB(); // Fecha a conexão com o banco de dados após o uso
            }
        }
    }

    public void buttonFornRemove(ActionEvent actionEvent) {
    }

    public void buttonFornEdit(ActionEvent actionEvent) {
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

        tableViewFornecedor.setItems(Settings.getListForn());
    }
}

package com.example.tgpsi_m19_luciano_bajurea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.tgpsi_m19_luciano_bajurea.ConexaoBD.*;

public class PrincipalController {
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
    private TableColumn tableColumnAdressClient;
    public TableColumn tableColumnNumberTelClient;
    public TableColumn tableColumnEmailCliente;
    public TextField clientId;
    public TextField clientName;
    public TextField clientNumTel;
    public TextField clientEmail;
    public Button btnAddClient;
    public Button btnRemoveCliente;
    public Button btnEditClient;
    public TextField clientAdress;
    public AnchorPane AnchorPaneFornecedor;
    public TableView tableViewFornecedor;
    public TableColumn tableColumnIdForn;
    public TableColumn tableColumnNameForn;
    public TableColumn tableColumnAdressForn;
    public TableColumn tableColumnNumTelForn;
    public TableColumn tableColumnEmailForn;
    public TextField FornecedorSearch;
    public TextField fornecedorId;
    public TextField fornecedorName;
    public TextField fornecedorNIF;
    public TextField fornecedorAdress;
    public TextField fornecedorNumTel;
    public TextField fornecedorEmail;
    public Button btnFornecedorAdd;
    public Button btnFornecedorRemove;
    public Button btnFornecedorEdit;
    public Button btnFornecedorDetalhe;

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

        alert.showAndWait().ifPresent(response ->{
            if(response == botaoSim){
                Settings.getPrimaryStage().close();
            }
        });
    }

    //-------------------------------------------------------------------------------
    // PRODUTO



    public void verProdutos(MouseEvent event) {
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
                    String newCategoria = String.valueOf(productCategoria.getSelectionModel().getSelectedItem());
                    double newPrice = Double.parseDouble(productPrice.getText());

                    // Cria um alerta de confirmação para a adição do produto
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAR");
                    alert.setHeaderText("Deseja mesmo adicionar este produto?");
                    alert.setContentText("Nome: " + newName + "\nPreço: " + newPrice + "\nDeseja mesmo adicionar?");
                    ButtonType buttonSim = new ButtonType("Sim");
                    ButtonType buttonNao = new ButtonType("Não");
                    alert.getButtonTypes().setAll(buttonSim, buttonNao);

                    // Exibe o alerta de confirmação e aguarda a escolha do utilizador
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == buttonSim) {
                            // Tenta adicionar o novo produto ao banco de dados
                            try {
                                String sql = "INSERT INTO produto (idProduto, nomeProduto, tipoProduto, precoProduto) VALUES (?, ?, ?, ?)";
                                PreparedStatement stmt = conn.prepareStatement(sql);
                                stmt.setInt(1, newId);
                                stmt.setString(2, newName);
                                stmt.setDouble(3, newPrice);
                                stmt.executeUpdate();

                                // Exibe um alerta informativo sobre a adição bem-sucedida
                                Alert alertAddProduct = new Alert(Alert.AlertType.INFORMATION);
                                alertAddProduct.setTitle("INFORMAÇÃO");
                                alertAddProduct.setHeaderText(null);
                                alertAddProduct.setContentText("Produto inserido com sucesso!");
                                alertAddProduct.showAndWait();

                                // Limpa os campos após a adição bem-sucedida
                                productId.clear();
                                productName.clear();
                                productCategoria.getSelectionModel().clearSelection();
                                productPrice.clear();
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
    public void buttonSeeDetailProduct(ActionEvent actionEvent) {
    }

    //----------------------------------------------------------------------------------------
    // CLIENTES
    public void verClientes(MouseEvent event) {
    }

    public void buttonAddClient(ActionEvent actionEvent) {
    }

    public void buttonRemoveClient(ActionEvent actionEvent) {
    }

    public void buttonEditClient(ActionEvent actionEvent) {
    }

    //----------------------------------------------------------------------------------------
    // FORNECEDOR
    public void verFornecedor(MouseEvent event) {
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
                    alert.setContentText("Nome: " + newName + "\nNIF: " + newNIF + "\nMorada: " +newAdress +"\nEmail:"+newEmail+"\nNúmero de Telemovel: " + newNumTel +"\nDeseja mesmo adicionar?");
                    ButtonType buttonSim = new ButtonType("Sim");
                    ButtonType buttonNao = new ButtonType("Não");
                    alert.getButtonTypes().setAll(buttonSim, buttonNao);

                    // Exibe o alerta de confirmação e aguarda a escolha do utilizador
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == buttonSim) {
                            // Tenta adicionar o novo produto ao banco de dados
                            try {
                                String sql = "INSERT INTO fornecedor (idFornecedor, nome, nif, morada, email, numTelemovel ) VALUES (?, ?, ?, ?)";
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
                                alertAddProduct.setContentText("Produto inserido com sucesso!");
                                alertAddProduct.showAndWait();

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

    public void buttonFornRemove(ActionEvent actionEvent) {
    }

    public void buttonFornEdit(ActionEvent actionEvent) {
    }
    public void buttonFornDetalhe(ActionEvent actionEvent) {
    }
}

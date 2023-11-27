package com.javafx.calc.mvc.Controller;

import com.javafx.calc.mvc.Model.Calculate;
import com.javafx.calc.mvc.Model.OperationsEnum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Controller {

    private Calculate calc;
    private Pane root;

    private final Double spacing_vbox = 2.0;
    private final Double hbox_maring = 2.0;
    Button btn_vir;
    Button btn_equ;

    private String output_text;
    private Label output;
    private Label lbl_output;
    Double val1, val2;

    OperationsEnum operations;
    boolean operationOn = false;

    private Pane hbox_txtOuput;
    private Pane hbox_btn1;
    private Pane hbox_btn2;
    private Pane hbox_btn3;
    private Pane hbox_btn4;

    private Font myFont;
    private Font myBtnFont = new Font("Arial", 16);

    private Button[] btn_numPad;
    private Button[] btn_functionsButton;

    public Controller() {

        initialize();
        btn_generator(btn_numPad);
        btn_operators_generator(btn_functionsButton);

        hbox_txtOuput.getChildren().addAll(lbl_output);
        hbox_btn1.getChildren().addAll(btn_numPad[7], btn_numPad[8], btn_numPad[9],
                btn_functionsButton[0]);
        hbox_btn2.getChildren().addAll(btn_numPad[4], btn_numPad[5], btn_numPad[6],
                btn_functionsButton[1]);
        hbox_btn3.getChildren().addAll(btn_numPad[1], btn_numPad[2], btn_numPad[3],
                btn_functionsButton[2]);
        hbox_btn4.getChildren().addAll(btn_vir, btn_numPad[0],
                btn_equ, btn_functionsButton[3]);

        add_spacing_center(btn_numPad);
        add_spacing_center(btn_vir, btn_equ);
        add_spacing_center(btn_equ);
        add_spacing_center(btn_functionsButton);
        add_spacing_center(hbox_txtOuput);
        add_spacing_center(root);
        add_spacing_center(hbox_btn4, btn_vir, btn_numPad[0], btn_equ, btn_functionsButton[3]);
        add_spacing_center(btn_numPad, hbox_btn1, hbox_btn2, hbox_btn3, hbox_btn4);
        add_spacing_center(btn_functionsButton, hbox_btn1, hbox_btn2, hbox_btn3, hbox_btn4);

        add_pane_border(hbox_txtOuput);
        root.getChildren().addAll(hbox_txtOuput, hbox_btn1, hbox_btn2, hbox_btn3,
                hbox_btn4);

        setVirAction(btn_vir);
        setEquAction(btn_equ);
        setDivActionOperation(btn_functionsButton[0]);
        setMultActionOperation(btn_functionsButton[1]);
        setMinActionOperation(btn_functionsButton[2]);
        setPlusActionOperation(btn_functionsButton[3]);

    }// end constructor

    /**
     * Ajouter un espacement au centre pour chaque bouton dans le pane
     * 
     * @param pane
     * @param btns
     */
    private void add_spacing_center(Pane pane, Button... btns) {
        //
        for (Button button : btns) {
            ((HBox) pane).setMargin(button, new Insets(hbox_maring));
        }
    }

    /**
     * Définir l'action de l'égalité pour le bouton
     * 
     * @param equ le bouton pour l'action d'égalité
     */
    private void setEquAction(Button equ) {
        equ.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                val2 = Double.parseDouble(lbl_output.getText());

                switch (operations) {
                    case PLUS:
                        output_text = String.valueOf(val1 + val2);
                        break;
                    case MINUS:
                        output_text = String.valueOf(val1 - val2);
                        break;
                    case MULT:
                        output_text = String.valueOf(val1 * val2);
                        break;
                    case DIV:
                        if (val2 == 0) {
                            output_text = String.valueOf(0);
                        } else {
                            output_text = String.valueOf(val1 / val2);

                        }
                        break;
                    default:
                        break;
                }
                lbl_output.setText(fixComa(output_text));
            }
        });
    }

    /**
     * Définir l'action de l'addition pour le bouton
     * 
     * @param button le bouton pour l'action d'addition
     */
    private void setPlusActionOperation(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                operation(OperationsEnum.PLUS);
            }
        });
    }

    /**
     * Définir l'action de la soustraction pour le bouton
     * 
     * @param button le bouton pour l'action de soustraction
     */
    private void setMinActionOperation(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                operation(OperationsEnum.MINUS);
            }
        });
    }

    /**
     * Définir l'action de la multiplication pour le bouton
     * 
     * @param button le bouton pour l'action de multiplication
     */
    private void setMultActionOperation(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                operation(OperationsEnum.MULT);
            }
        });
    }

    /**
     * Définir l'action de la division pour le bouton
     * 
     * @param button le bouton pour l'action de division
     */
    private void setDivActionOperation(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                operation(OperationsEnum.DIV);
            }
        });
    }

    /**
     * Effectue une opération sur le nombre affiché en fonction de l'opération
     * spécifiée
     * 
     * @param oper l'opération à effectuer
     */
    private void operation(OperationsEnum oper) {
        val1 = Double.parseDouble(lbl_output.getText());
        operations = oper;
        operationOn = true;
    }

    /**
     * Définir l'action du bouton virgule
     * 
     * @param vir_btn le bouton pour l'action de la virgule
     */
    private void setVirAction(Button vir_btn) {
        vir_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                printOutput_vir(((Button) event.getSource()).getText());
            }
        });
    }

    /**
     * Imprime la sortie avec la virgule si elle n'existe pas déjà
     * 
     * @param vir la virgule à ajouter
     */
    private void printOutput_vir(String vir) {
        output_text = lbl_output.getText();
        if (!hasComa(output_text)) {
            output_text = lbl_output.getText() + vir;
        }
        lbl_output.setText(output_text);
    }

    /**
     * Vérifie si le nombre contient une virgule
     * 
     * @param number le nombre à vérifier
     * @return true si le nombre contient une virgule, sinon false
     */
    private boolean hasComa(String number) {
        return number.contains(".");
    }

    /**
     * Ajoute un espacement au centre pour le pane spécifié
     * 
     * @param pane le pane pour lequel l'espacement doit être ajouté
     */
    private void add_spacing_center(Pane pane) {
        if (pane instanceof HBox) {
            ((HBox) pane).setAlignment(Pos.CENTER_RIGHT);
            ((HBox) pane).setMaxWidth(250);
            ((HBox) pane).setMargin(lbl_output, new Insets(0, 20, 0, 0));
        } else if (pane instanceof VBox) {
            ((VBox) pane).setAlignment(Pos.CENTER);
        }
    }

    /**
     * Ajoute un espacement au centre pour les boutons dans les panneaux spécifiés
     * 
     * @param btns les boutons pour lesquels l'espacement doit être ajouté
     * @param panes les panneaux pour lesquels l'espacement doit être ajouté
     */
    private void add_spacing_center(Button[] btns, Pane... panes) {
        for (Button btn : btns) {
            for (Pane pane : panes) {
                if (pane instanceof HBox) {
                    ((HBox) pane).setAlignment(Pos.CENTER);
                    ((HBox) pane).setMargin(btn, new Insets(hbox_maring));
                } else if (pane instanceof VBox) {
                    ((VBox) pane).setAlignment(Pos.CENTER);
                }
            }
        }
    }

    /**
     * Ajoute une bordure au pane spécifié
     * 
     * @param pane_txt_output_hbox le pane auquel la bordure doit être ajoutée
     */
    private void add_pane_border(Pane pane_txt_output_hbox) {
        pane_txt_output_hbox.setPrefSize(150, 50);
        BorderStroke border_strStroke = new BorderStroke(
                Color.ANTIQUEWHITE,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(1));
        pane_txt_output_hbox.setBorder(new Border(border_strStroke));
    }

    /**
     * Ajoute un espacement au centre pour les boutons spécifiés
     * 
     * @param btns les boutons pour lesquels l'espacement doit être ajouté
     */
    private void add_spacing_center(Button... btns) {
        for (Button button : btns) {
            if (button instanceof Button) {
                ((Button) button).setPrefSize(50, 50);
                ((Button) button).setFont(myBtnFont);
                ((Button) button).setAlignment(Pos.CENTER);
            }
        }
    }

    /**
     * Génère les boutons pour les chiffres
     * 
     * @param btns les boutons à générer
     */
    private void btn_generator(Button[] btns) {
        for (int i = 0; i < btns.length; i++) {
            btn_numPad[i] = new Button("" + i);
            btn_numPad[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // output_text = ((Button)event.getSource()).getText();
                    printOutput(((Button) event.getSource()).getText());
                }
            });
        }
    }

    private void btn_operators_generator(Button[] btn_operators) {
        btn_operators[0] = new Button("/");
        btn_operators[1] = new Button("*");
        btn_operators[2] = new Button("-");
        btn_operators[3] = new Button("+");

    }

    /**
     * Imprime la sortie sur l'écran en fonction du nombre donné
     * 
     * @param number le nombre à afficher
     */
    private void printOutput(String number) {
        if (lbl_output.getText().equals("0") || operationOn) {
            output_text = number;
            operationOn = false;
        } else {
            output_text = lbl_output.getText() + number;
        }
        lbl_output.setText(output_text);
    }

    /**
     * Fixe la virgule dans le nombre donné
     * 
     * @param number le nombre à corriger
     * @return le nombre corrigé
     */
    private String fixComa(String number) {
        if (Double.parseDouble(number) == Math.floor(Double.parseDouble(number))) {
            number = number.substring(0, output_text.length() - 2);
        }
        return number;
    }

    /**
     * Initialise le contrôleur principal
     * 
     */
    private void initialize() {
        root = new VBox(spacing_vbox);
        calc = new Calculate();

        hbox_txtOuput = new HBox(3);
        hbox_btn1 = new HBox();
        hbox_btn2 = new HBox();
        hbox_btn3 = new HBox();
        hbox_btn4 = new HBox();

        calc.setOutput(new Label("0"));
        lbl_output = calc.getOutput();

        myFont = new Font("Arial", 26);
        Font myLabelFont = new Font("Arial", 14);
        lbl_output.setFont(myFont);
        lbl_output.setText(output_text);
        btn_vir = new Button(".");
        btn_equ = new Button("=");

        calc.setBtn_numPad(new Button[10]);
        btn_numPad = calc.getBtn_numPad();

        calc.setBtn_funtionsButton(new Button[4]);
        btn_functionsButton = calc.getBtn_funtionsButton();
        lbl_output = new Label();
        lbl_output.setFont(myFont);
        lbl_output.setText("0");
        output = new Label();
        output.setFont(myLabelFont);
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        root = root;
    }

}

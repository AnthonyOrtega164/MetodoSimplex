package Controlador;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;

public class control_restricciones extends JDialog {

    private JTextField textField;

    public control_restricciones(Frame padre) {

        super(padre, true);
        setTitle("Restricciones");
        textField = new JTextField(24);
        getContentPane().add(textField);

        textField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });
    }

    public String getText() {
        return textField.getText();
    }
}

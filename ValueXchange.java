import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ValueXchange {

    private JFrame frame;
    private JButton btn;
    private JComboBox<Moneda> cmb;
    private JLabel lbl;
    private JTextField txt; // Corrected the type to JTextField

    public enum Moneda {
        peso_dolar, peso_euro, peso_libraEsterlina, peso_yenJapones, peso_WonSurCoreano, dolar_peso, euro_peso,
        libraEsterlina_peso, yenJapones_peso, WonSurCoreano_peso,
    }

    public double dolar = 16.72;
    public double euro = 18.73;
    public double libraEsterlina = 21.64;
    public double yenJapones = 0.12;
    public double WonSurCoreano = 76.49;

    public double valorInput;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ValueXchange window = new ValueXchange();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ValueXchange() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txt = new JTextField(); // Initialize 'txt'
        txt.setBounds(90, 71, 122, 20);
        frame.getContentPane().add(txt);
        txt.setColumns(10);

        cmb = new JComboBox<Moneda>();
        cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
        cmb.setBounds(90, 102, 122, 22);
        frame.getContentPane().add(cmb);

        btn = new JButton("Convertir");
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Convertir();
            }
        });
        btn.setBounds(222, 102, 89, 23);
        frame.getContentPane().add(btn);

        lbl = new JLabel("00.00");
        lbl.setBounds(222, 74, 191, 14);
        frame.getContentPane().add(lbl);
    }

    public void Convertir() {
        if (Validar(txt.getText())) {
            Moneda moneda = (Moneda) cmb.getSelectedItem();
            switch (moneda) {
                case peso_dolar:
                    PesoAMoneda(dolar);
                    break;
                case peso_euro:
                    PesoAMoneda(euro);
                    break;
                case peso_libraEsterlina:
                    PesoAMoneda(libraEsterlina);
                    break;
                case peso_yenJapones:
                    PesoAMoneda(yenJapones);
                    break;
                case peso_WonSurCoreano:
                    PesoAMoneda(WonSurCoreano);
                    break;
                case dolar_peso:
                    MonedaAPeso(dolar);
                    break;
                case euro_peso:
                    MonedaAPeso(euro);
                    break;
                case libraEsterlina_peso:
                    MonedaAPeso(libraEsterlina);
                    break;
                case yenJapones_peso:
                    MonedaAPeso(yenJapones);
                    break;
                case WonSurCoreano_peso:
                    MonedaAPeso(WonSurCoreano);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + moneda);
            }
        }
    }

    public void PesoAMoneda(double moneda) {
        double res = valorInput / moneda;
        lbl.setText(Redondear(res));
    }

    public void MonedaAPeso(double moneda) {
        double res = valorInput * moneda;
        lbl.setText(Redondear(res));
    }

    public String Redondear(double valor) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(valor);
    }

    public boolean Validar(String texto) {
        try {
            double x = Double.parseDouble(texto);
            if (x > 0) {
                valorInput = x;
                return true;
            } else {
                lbl.setText("Ingrese un número positivo");
                return false;
            }
        } catch (NumberFormatException e) {
            lbl.setText("¡Solamente números!");
            return false;
        }
    }
}

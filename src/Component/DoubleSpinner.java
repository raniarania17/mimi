/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author benso
 */
public class DoubleSpinner extends JSpinner{
     private static final long serialVersionUID = 1L;
    private static final double STEP_RATIO = 0.1;

    private SpinnerNumberModel model;

    public DoubleSpinner() {
        super();
        // Model setup
        model = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);
        this.setModel(model);

        // Step recalculation
        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double value = getDouble();
                // Steps are sensitive to the current magnitude of the value
                long magnitude = Math.round(Math.log10(value));
                double stepSize = STEP_RATIO * Math.pow(10, magnitude);
                model.setStepSize(stepSize);
            }
        });
    }
     public Double getDouble() {
        return (Double)getValue();
    }
}

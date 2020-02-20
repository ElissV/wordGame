package com.example.gui;

import com.example.Main;
import com.example.WordCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameForm {

    private JFrame jFrame;
    private JTextField answerInputField;
    private JLabel scoreLabel;
    private JLabel timeLeftLabel;
    private JSplitPane splitPane;
    private JTextArea textArea;

    public GameForm () {
        int frameWidth = 700, frameHeight = 450;
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(frameWidth, frameHeight);
        jFrame.setTitle("Word Game");
        jFrameCentralize();
        placeElements();
        jFrame.setVisible(true);
        answerInputField.requestFocusInWindow();
    }

    private void jFrameCentralize() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width/2)-(jFrame.getSize().width/2);
        int y = (dim.height/2)-(jFrame.getSize().height/2);
        jFrame.setLocation(x, y);
    }

    private void placeElements() {
        Dimension labelDimension = new Dimension(550, 20);
        Dimension textAreaDimension = new Dimension(100, 350);
        Font labelFont = new Font("serif", Font.BOLD, 18);

        Container contentPane = jFrame.getContentPane();
        timeLeftLabel.setFont(labelFont);
        scoreLabel.setFont(labelFont);
        scoreLabel.setMinimumSize(labelDimension);
        contentPane.add(splitPane, BorderLayout.NORTH);

        InputCheck inputCheck = new InputCheck();
        answerInputField.addActionListener(inputCheck.answerSubmittingAction);
        contentPane.add(answerInputField, BorderLayout.CENTER);
        textArea.setPreferredSize(textAreaDimension);
        contentPane.add(textArea, BorderLayout.PAGE_END);
    }

    private class InputCheck {

        Action answerSubmittingAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable inputHandlerRunnable =
                        () -> {
                            String answer = answerInputField.getText();
                            updateAfterAnswerSubmitting(answer);
                            WordCheck wordCheck = Main.getWordCheck();
                            wordCheck.checkForCorrectAnswer(answer);
                            scoreLabel.setText("Score: " + Main.getScore());
                        };
                new Thread(inputHandlerRunnable).start();
            }
        };

        private void updateAfterAnswerSubmitting(String answer) {
            answerInputField.setText("");
            String output = textArea.getText();
            textArea.setText(answer + "\n" + output);
        }

    }

    public JFrame getJFrame() { return jFrame; }

    public JLabel getTimeLeftLabel() {
        return timeLeftLabel;
    }

    public JTextField getAnswerInputField() {
        return answerInputField;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
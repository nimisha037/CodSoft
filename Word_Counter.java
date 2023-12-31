import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class word_counter {
    private static JTextArea outputTextArea;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame fr = new JFrame("Word Counter");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(new BorderLayout());

        JPanel tPanel = new JPanel();
        tPanel.setLayout(new FlowLayout());

        JLabel textLabel = new JLabel("Enter your text:");
        tPanel.add(textLabel);

        JTextArea textArea = new JTextArea(10, 40);
        tPanel.add(new JScrollPane(textArea));

        fr.add(tPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton processTB = new JButton("Process Text");
        processTB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textArea.getText();
                String[] wordArray = countWords(inputText);
                displayStats(wordArray);
            }
        });
        buttonPanel.add(processTB);

        JButton processFBtn = new JButton("Process File");
        processFBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fChooser = new JFileChooser();
                int returnValue = fChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fChooser.getSelectedFile();
                    String fileText = readFile(selectedFile);
                    String[] wordArray = countWords(fileText);
                    displayStats(wordArray);
                }
            }
        });
        buttonPanel.add(processFBtn);

        fr.add(buttonPanel, BorderLayout.CENTER);

        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        fr.add(new JScrollPane(outputTextArea), BorderLayout.SOUTH);

        fr.pack();
        fr.setVisible(true);
    }

    private static String[] countWords(String text) {
        return text.toLowerCase().split("[\\p{Punct}\\s]+");
    }

    private static String readFile(File file) {
        StringBuilder fileContent = new StringBuilder();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                fileContent.append(sc.nextLine()).append("\n");
            }
        } catch (FileNotFoundException exp) {
            exp.printStackTrace();
        }
        return fileContent.toString();
    }

    private static void displayStats(String[] wordArray) {
        int totalWords = wordArray.length;
        Set<String> unique = new HashSet<>(Arrays.asList(wordArray));
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : wordArray) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        StringBuilder stats = new StringBuilder();
        stats.append("Total words: ").append(totalWords).append("\n");
        stats.append("Unique words: ").append(unique.size()).append("\n\n");
        stats.append("Frequency of each word:\n");
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            stats.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        outputTextArea.setText(stats.toString());
    }
}

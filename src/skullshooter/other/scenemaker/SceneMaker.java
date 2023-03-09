package skullshooter.other.scenemaker;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

public class SceneMaker {

    private static class PropertyNode extends DefaultMutableTreeNode {

        private int x, y, sX, sY;

        public PropertyNode(String text, int x, int y, int sX, int sY) {
            super(text);
            this.x = x;
            this.y = y;
            this.sX = sX;
            this.sY = sY;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getsX() {
            return sX;
        }

        public void setsX(int sX) {
            this.sX = sX;
        }

        public int getsY() {
            return sY;
        }

        public void setsY(int sY) {
            this.sY = sY;
        }
    }

    private static DefaultMutableTreeNode root;
    private static DefaultTreeModel model;
    private static TreePath selectedPath;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SceneMaker");
        JTree tree = new JTree();

        JPanel mainPane = new JPanel();
        mainPane.setLayout(new BorderLayout());
        mainPane.setPreferredSize(new Dimension(1280, 720));

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(20, 0));

        JButton addEntity = new JButton("New Entity");
        addEntity.addActionListener(e -> {
            String name = input("Name:", frame);
            if (name == null) return;

            int x = Integer.parseInt(input("X:", frame));
            int y = Integer.parseInt(input("Y:", frame));
            int sX = Integer.parseInt(input("Size X:", frame));
            int sY = Integer.parseInt(input("Size Y:", frame));

            model.insertNodeInto(new PropertyNode(name, x, y, sX, sY), root, root.getChildCount());
        });
        sidebar.add(addEntity);

        JButton addComponent = new JButton("New Component");
        addComponent.addActionListener(e -> {
            Object[] path = selectedPath.getPath();

            if (path.length == 2) {
                String className = input("Class name:", frame);
                if (className == null) return;

                model.insertNodeInto(new DefaultMutableTreeNode(className), (PropertyNode)path[1], ((PropertyNode) path[1]).getChildCount());
            }
        });
        sidebar.add(addComponent);

        JButton newScene = new JButton("New Scene");
        newScene.addActionListener(e -> {
            root = new DefaultMutableTreeNode("skullshooter.engine.Scene");
            model = new DefaultTreeModel(root);
            tree.setModel(model);
        });
        sidebar.add(newScene);

        JButton build = new JButton("Build");
        build.addActionListener(e -> {
            JTextArea area = new JTextArea();

            StringBuilder builder = new StringBuilder("new Scene(\n");
            for (int i = 0; i < root.getChildCount(); i++) {
                PropertyNode node = (PropertyNode) root.getChildAt(i);

                builder.append("    new Entity(\"").append(node.toString()).append("\", ").append(node.getX()).append(", ").append(node.getY()).append(", ").append(node.getsX()).append(", ").append(node.getsY());
                if (node.getChildCount() > 0)
                    builder.append(",\n");

                for (int j = 0; j < node.getChildCount(); j++) {
                    DefaultMutableTreeNode node1 = (DefaultMutableTreeNode) node.getChildAt(j);

                    builder.append("        new ").append(node1.toString()).append("(\"").append(node1).append("\")");
                    if (j < node.getChildCount() - 1)
                        builder.append(",");
                    builder.append("\n");
                }
                builder.append("    )");
                if (i < root.getChildCount() - 1)
                    builder.append(",");
                builder.append("\n");
            }
            builder.append(")");

            area.setText(builder.toString());

            JOptionPane.showMessageDialog(frame, area, "Result", JOptionPane.INFORMATION_MESSAGE);
        });
        sidebar.add(build);

        mainPane.add(sidebar, BorderLayout.WEST);

        tree.addTreeSelectionListener(e -> selectedPath = e.getPath());
        mainPane.add(tree);

        root = new DefaultMutableTreeNode("skullshooter.engine.Scene");
        model = new DefaultTreeModel(root);
        tree.setModel(model);

        frame.add(mainPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static String input(String title, Component parent) {
        String str = JOptionPane.showInputDialog(parent, title, title, JOptionPane.INFORMATION_MESSAGE);
        return str;
    }
}
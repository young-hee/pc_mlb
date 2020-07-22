package ncp_config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import lombok.Data;

public class PropertiesToYamlConverter {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties src = new Properties();
		src.load(new FileInputStream(new File("src/main/resources/META-INF/config/ncp_base/prod.properties")));

		Node root = new Node();

		Enumeration<Object> keys = src.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			List<String> keyPath = new ArrayList(Arrays.asList(key.split("\\.")));
			root.findNode(keyPath).setValue(src.getProperty(key));
		}
		System.out.println(root);
	}

}

@Data
class Node {
	Node parent;
	String key;
	String value;
	List<Node> childs = new ArrayList<>();

	public Node findNode(List<String> keyPath) {
		if (keyPath.size() == 0) {
			return this;
		}
		String key = keyPath.remove(0);
		Node child = findChild(key);
		return child.findNode(keyPath);

	}

	private Node findChild(String targetKey) {
		for (Node child : childs) {
			if (targetKey.equals(child.getKey())) {
				return child;
			}
		}
		Node node = new Node();
		node.setParent(this);
		node.setKey(targetKey);
		childs.add(node);
		return node;
	}

	int depth() {
		int depth = 0;
		Node parent = this.parent;
		while (parent != null) {
			depth++;
			parent = parent.parent;
		}
		return depth;
	}

	public void toString(StringBuffer buf) {

		int depth = depth();
		for (int i = 0; i < depth; i++) {
			buf.append("  ");
		}
		buf.append(key + ":");
		if (value != null) {
			buf.append(" " + value + "\n");
		} else {
			buf.append("\n");
			for (Node child : childs) {
				child.toString(buf);
			}
		}
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		toString(buf);
		return buf.toString();
	}

}

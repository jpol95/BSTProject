package sample;

import java.util.HashMap;
import java.util.Map;

public class PlacementCalc {
    private Map<String, Node> directions;
    public PlacementCalc(){
        directions = new HashMap<>();
    }
    public Map<String, Node> getDirections() {
        return directions;
    }

    void createDirections(Node root, String direction){
        if (root == null) return;
        directions.put(direction, root);
        createDirections(root.left, direction + "l");
        createDirections(root.right, direction + "r");
    }
    Double xPos(String s){
        if (s.equals("")) return 0.5;
        int hPlace = 2*encodeToBinary(s) + 1;
        return (hPlace)/Math.pow(2,s.length() + 1);
    }

    int encodeToBinary(String s){
        String result = "";
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == 'l') result += "0";
            if (s.charAt(i) == 'r') result += "1";
        }
        return Integer.parseInt(result, 2);
    }

}

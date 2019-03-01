public class Pole3D {
    public static boolean equalsIgnoreCase(String[][][] a, String[][][] b){
        if(a == null && b == null){
            return true;
        }
        if(a == null || b == null) {
            return false;
        }
        if(a.length != b.length){
            return false;
        }

        for(int i = 0; i < a.length; i++){
            if(a[i] == null && b[i] == null){
                continue;
            }
            if(a[i] == null || b[i] == null){
                return false;
            }
            if(a[i].length != b[i].length){
                return false;
            }
            for(int j = 0; j < a[i].length; j++){
                if(a[i][j] == null && b[i][j] == null){
                    continue;
                }
                if(a[i][j] == null || b[i][j] == null){
                    return false;
                }
                if(a[i][j].length != b[i][j].length){
                    return false;
                }
                for(int k = 0; k < a[i][j].length; k++){
                    if(a[i][j][k] == null && b[i][j][k] == null){
                        continue;
                    }
                    if(a[i][j][k] == null || b[i][j][k] == null){
                        return false;
                    }
                    if(!a[i][j][k].equalsIgnoreCase(b[i][j][k])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        {
            String pole1[][][] = {{{"ahoj"}}};
            String pole2[][][] = {{{"aHoj"}}};

            System.out.println(equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = {{{"ahoj"}, {null}}};
            String pole2[][][] = {{{"aHoj"}, {    }}};

            System.out.println(equalsIgnoreCase(pole1, pole2));
        }
    }
}

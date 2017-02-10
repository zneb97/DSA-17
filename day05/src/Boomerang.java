public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        MyHashMap<Integer, Integer> hash = new MyHashMap<>();
        int count = 0;

       for(int i = 0; i < points.length; i++) {
           hash.clear();
           for (int j = 0; j < points.length; j++) {
               //Populate hash with point and distance
               int distance = getSquaredDistance(points[i], points[j]);
               if (hash.containsKey(distance)) {
                   hash.put(distance, hash.get(distance) + 1);
               } else {
                   hash.put(distance, 1);
               }
           }
           //Check to see which counters actually incremented
           for (Integer counters : hash.values()) {
               count += counters * (counters - 1);
           }
       }
       return count;
    }

    private static int getSquaredDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx*dx + dy*dy;
    }
}

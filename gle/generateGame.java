    public static void generate(int m, int n, int color) throws Exception {
        if(color == 1 && (m >= 3 || n >= 3)){
            throw new Exception("impossible!");
        }

        int[][] nums = new int[m][n];
        //从左到右扫只需要考虑上面和左面
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = randomGen(color);
                if (i < 2 && j < 2) {
                    nums[i][j] = randomGen(color);
                } // consider only left
                else if (i < 2) {
                    if (nums[i][j - 1] == nums[i][j - 2]) {
                        while (nums[i][j] == nums[i][j - 1]) {
                            nums[i][j] = randomGen(color);
                        }
                    }
                } else if (j < 2) {
                    if (nums[i - 1][j] == nums[i - 2][j]) {
                        while (nums[i][j] == nums[i - 1][j]) {
                            nums[i][j] = randomGen(color);
                        }
                    }
                } else {
                    HashSet<Integer> set = new HashSet();
                    if (nums[i][j - 1] == nums[i][j - 2]) {
                        set.add(nums[i][j - 1]);
                    }
                    if (nums[i - 1][j] == nums[i - 2][j]) {
                        set.add(nums[i - 1][j]);
                    }
                    while (set.contains(nums[i][j])) {
                        nums[i][j] = randomGen(color);
                    }
                }
            }
        }
        for (int[] arr : nums) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public  static int randomGen(int color) {
        return (int) (Math.random() * color); // Mathrandom() >=0  < 1;
    }
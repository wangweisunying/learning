public static double twentyOne(int k) {
        double[] f = new double[k + 1];
        f[0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= 10; j++) {
                if (i - j >= 0) {
                    f[i] += f[i - j] * 0.1;
                }
            }
        }
        return f[k];
    }
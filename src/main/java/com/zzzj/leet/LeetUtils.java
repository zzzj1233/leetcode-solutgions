package com.zzzj.leet;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.zzzj.util.ExecutionCallback;
import com.zzzj.util.InvokableExp;
import com.zzzj.util.InvokeMethodSource;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zzzj
 * @create 2021-10-25 10:08
 */
public class LeetUtils {

    public static Random random = new Random();

    public static int[] newArray(int n) {
        return newArray(n, 10);
    }

    public static int[] newArray(int n, int m) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = random.nextInt(m) + 1;
        }
        return result;
    }

    public static String newString(int n, int max) {
        char[] result = new char[n];
        for (int i = 0; i < n; i++) {
            result[i] = (char) (random.nextInt(max) + 97);
        }
        return String.valueOf(result);
    }

    public static String newString(int n) {
        char[] result = new char[n];
        for (int i = 0; i < n; i++) {
            result[i] = (char) (random.nextInt(26) + 97);
        }
        return String.valueOf(result);
    }


    public static String randomTreeStr(int n) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && random.nextInt(10) == 9) {
                list.add("null");
            } else {
                list.add(String.valueOf(random.nextInt(100)));
            }
        }

        String str = String.join(",", list);

        return "[" + str + "]";
    }

    public static TreeNode randomTree(int n) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && random.nextInt(10) == 9) {
                list.add("null");
            } else {
                list.add(String.valueOf(random.nextInt(100)));
            }
        }

        String str = String.join(",", list);

        return TreeNode.buildTree("[" + str + "]");
    }

    public static com.zzzj.tree.TreeNode randomTree2(int n) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i > 0 && random.nextInt(10) == 9) {
                list.add("null");
            } else {
                list.add("0");
            }
        }

        String str = String.join(",", list);

        return com.zzzj.tree.TreeNode.buildTree("[" + str + "]");
    }

    public static String randomFullTree() {
        ArrayList<String> list = new ArrayList<>();

        int N = random.nextInt(100);

        for (int i = 0; i < N; i++) {
            list.add(String.valueOf(random.nextInt(100)));
        }

        String str = String.join(",", list);

        return "[" + str + "]";
    }


    public static String randomNumberString(int length) {
        String str = "0123456789";

        String res = randomString0(str, length);

        while (res.startsWith("0"))
            res = randomString0(str, length);

        return res;
    }

    public static String randomUpperString(int length) {
        return randomString0("ABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }

    // 是否包含大写字母
    public static String randomString(int length, boolean containsUpper) {
        String str;

        if (containsUpper) {
            str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        } else {
            str = "abcdefghijklmnopqrstuvwxyz";
        }


        return randomString0(str, length);
    }

    public static String randomLowerString(int length, int candidateLen) {
        String candidate = "abcdefghijklmnopqrstuvwxyz".substring(0, candidateLen);
        return randomString0(candidate, length);
    }

    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        return randomString0(str, length);
    }

    public static String randomString0(String candidate, int length) {
        Random random = new Random();

        StringBuffer sb = new StringBuffer(length);

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(candidate.length());

            sb.append(candidate.charAt(number));
        }

        return sb.toString();
    }

    public static String randomString(int length, String candidate) {
        return randomString(length, candidate, true);
    }

    public static String randomString(int length, String candidate, boolean allowRepeat) {
        if (!allowRepeat && candidate.length() < length) {
            throw new IllegalArgumentException();
        }
        Random random = new Random();

        StringBuffer sb = new StringBuffer(length);

        boolean[] used = new boolean[26];

        while (sb.length() < length) {
            int number = random.nextInt(candidate.length());
            char c = candidate.charAt(number);
            if (!allowRepeat) {
                if (used[c - 'a']) {
                    continue;
                }
                used[c - 'a'] = true;
            }
            sb.append(c);
        }

        return sb.toString();
    }


    public static int[] randomBinaryArr(int n) {
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            if (random.nextInt() % 2 == 0) {
                result[i] = 1;
            }
        }

        return result;
    }

    public static int[][] random2DInts(int M, int N, int rangeL, int rangeR) {
        int[][] result = new int[M][N];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = random.nextInt(rangeR) + rangeL;
            }
        }

        return result;
    }

    public static char[][] random2DChars(int M, int N, String candidate) {
        char[][] result = new char[M][N];

        for (int i = 0; i < result.length; i++) {

            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = candidate.charAt(random.nextInt(candidate.length()));
            }

        }

        return result;
    }

    public static char[][] random2DChars(int M, int N, boolean containsUpper) {
        return random2DChars(M, N, containsUpper ? "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" : "abcdefghijklmnopqrstuvwxyz");
    }


    public static char[] convertChars1(String source) {
        return convertChars("[" + source + "]")[0];
    }


    // source e.g : [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
    public static char[][] convertChars(String source) {

        source = source.substring(1, source.length() - 1);

        String[] split = source.split("],?");

        char[][] result = new char[split.length][];

        for (int i = 0; i < split.length; i++) {
            String oneD = split[i].trim().substring(1);

            String[] chars = oneD.split(",");

            // 去除双引号
            result[i] = new char[chars.length];

            for (int j = 0; j < chars.length; j++) {
                String singleChar = chars[j].trim().replaceAll("\"", "");
                result[i][j] = singleChar.charAt(0);
            }

        }

        return result;
    }

    public static List<List<String>> convertListStrings(String source) {
        source = source.substring(1, source.length() - 1);

        String[] split = source.split("]\\s*,?");

        List<List<String>> result = new ArrayList<>(split.length);

        for (int i = 0; i < split.length; i++) {
            String oneD = split[i].replaceAll("\\s*", "")
                    .replaceAll("\"", "")
                    .replaceAll("\\[", "").replaceAll("\"", "");
            String[] chars = oneD.split(",\\s*");
            // 去除双引号
            ArrayList<String> list = new ArrayList<>(chars.length);

            result.add(list);

            for (int j = 0; j < chars.length; j++) {
                list.add(chars[j].trim());
            }
        }

        return result;
    }

    public static List<Integer> convertLists0(String source) {
        return convertLists("[" + source + "]").get(0);
    }

    public static List<List<Integer>> convertLists(String source) {
        source = source.substring(1, source.length() - 1);

        String[] split = source.split("]\\s*,?");

        List<List<Integer>> result = new ArrayList<>(split.length);

        for (int i = 0; i < split.length; i++) {
            String oneD = split[i].replaceAll("\\s*", "").replaceAll("\\[", "");
            String[] chars = oneD.split(",\\s*");
            // 去除双引号
            ArrayList<Integer> list = new ArrayList<>(chars.length);
            result.add(list);

            for (int j = 0; j < chars.length; j++) {
                list.add(Integer.parseInt(chars[j].trim()));
            }
        }

        return result;
    }

    public static int[] convertInts1(String source) {
        return convertInts("[" + source + "]")[0];
    }

    // e.g : [[4, 2, 4], [2, 3, 5], [4, 4, 6]]
    public static int[][] convertInts(String source) {
        source = source.substring(1, source.length() - 1);

        String[] split = source.split("]\\s*,?");

        int[][] result = new int[split.length][];

        for (int i = 0; i < split.length; i++) {
            String oneD = split[i].replaceAll("\\s*", "").replaceAll("\\[", "");

            if (oneD.isEmpty()) {
                result[i] = new int[0];
                continue;
            }

            String[] chars = oneD.split(",\\s*");
            // 去除双引号
            result[i] = new int[chars.length];

            for (int j = 0; j < chars.length; j++) {
                result[i][j] = Integer.parseInt(chars[j].trim());
            }

        }

        return result;
    }

    public static String[] convertString1(String source) {
        return convertListStrings("[" + source + "]").get(0).toArray(new String[0]);
    }

    public static String charsToLeetCode(char[][] chars) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (int i = 0; i < chars.length; i++) {
            String str = charsToLeetCode(chars[i]);
            builder.append(str);
            if (i < chars.length - 1) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();
    }

    public static String charsToLeetCode(char[] chars) {
        StringBuilder builder = new StringBuilder((chars.length << 2) + 1);

        builder.append("[");

        for (char aChar : chars) {
            builder.append("\"");
            builder.append(aChar);
            builder.append("\"");
            builder.append(",");
        }

        builder.setLength(builder.length() - 1);

        builder.append("]");

        return builder.toString();
    }

    public static int[][] randomMatrix(int N, int M, int rangL, int rangR) {
        int[][] result = new int[N][M];

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {
                result[i][j] = random.nextInt(rangR) + rangL;
            }

        }

        return result;
    }

    public static char[][] intsToChars(int[][] ints) {
        int N = ints.length;
        int M = ints[0].length;

        char[][] res = new char[N][M];

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {
                res[i][j] = Character.forDigit(ints[i][j], 10);
            }

        }

        return res;
    }

    public static void shuffle(int[] nums) {
        for (int i = 0; i < nums.length / 2; i++) {
            // 每次只需拿第一个元素进行交换即可
            swap(nums, 0, random.nextInt(nums.length));
        }
    }


    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static TreeNode randomTree(int N, int rangeL, int rangeR) {
        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = String.valueOf(random.nextInt(rangeR) + rangeL);
        }

        return TreeNode.buildTree(ArrayUtil.join(arr, StrUtil.COMMA));
    }

    public static LinkedList<Object> executeExpression(String expression, String arguments, Object instance) {
        if (expression.startsWith("[")) {
            expression = expression.substring(1, expression.length() - 1);
        }

        if (arguments.startsWith("[")) {
            arguments = arguments.substring(1, arguments.length() - 1);
        }

        List<String> items = StrUtil.split(expression, ",")
                .stream().map(s -> StrUtil.removeAll(s, '"', ' '))
                .collect(Collectors.toList());

        List<String> args = StrUtil.split(arguments, "],")
                .stream().map(s -> StrUtil.removeAll(s, '"', ' ', '[', ']'))
                .collect(Collectors.toList());

        LinkedList<Object> resultList = new LinkedList<>();

        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);

            if (item.equals(instance.getClass().getSimpleName())) {
                continue;
            }

            Method method = ReflectUtil.getMethodByName(instance.getClass(), item);

            Class<?>[] types = method.getParameterTypes();

            Object result;
            if (types.length == 0) {
                result = ReflectUtil.invoke(instance, method);
            } else {

                String methodArgs = args.get(i);

                String[] split = methodArgs.split(",");

                Object[] invokeArgs = new Object[split.length];

                for (int j = 0; j < invokeArgs.length; j++) {
                    invokeArgs[j] = Convert.convert(types[j], split[j]);
                }

                result = ReflectUtil.invoke(instance, method, invokeArgs);
            }
            if (result != null) {
                resultList.add(result);
            }
        }

        return resultList;
    }

    public static int[] listToArray(List<Integer> list) {
        int N = list.size();

        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static String stringsToLeetCode(String[] arr) {
        return "[" + Arrays.stream(arr).map(s -> "\"" + s + "\"").collect(Collectors.joining(",")) + "]";
    }

    public static String stringToLeetCode(String str) {
        return "\"" + str + "\"";
    }

    public static String stringsToLeetCode(List<String> list) {
        return "[" + list.stream().map(s -> "\"" + s + "\"").collect(Collectors.joining(",")) + "]";
    }

    public static String toBinaryString(long num, int bitSize) {
        StringBuilder builder = new StringBuilder(bitSize);

        for (int i = 0; i < bitSize; i++) {
            builder.append(((num >> i) & 1) == 1 ? "1" : "0");
        }

        return builder.reverse().toString();
    }

    public static String toBinaryString(int num) {
        return toBinaryString(num, Integer.SIZE);
    }

    public static boolean executeExpression(InvokableExp exp, InvokableExp expect, int N, InvokeMethodSource... sources) {
        int length = sources.length;

        for (int i = 0; i < N; i++) {
            InvokeMethodSource source = sources[random.nextInt(length)];

            String methodName = source.getMethodName();

            Object[] parameters = source.getParamsSupplier().get();

            Object result = null;

            try {
                result = exp.invokeMethod(methodName, parameters);
            } catch (Exception e) {
                throw e;
            }

            Object expectResult = null;
            try {
                expectResult = expect.invokeMethod(methodName, parameters);
            } catch (Exception e) {
                throw e;
            }

            if (result == null && expectResult == null) {
                continue;
            }

            boolean hasError = result == null;
            hasError |= result != null && expectResult == null;
            hasError |= result.getClass().isPrimitive() && result != expectResult;
            hasError |= !result.getClass().isPrimitive() && !result.equals(expectResult);

            if (hasError) {

                System.out.println("Error");

                System.out.println("method = " + methodName + " , params = " + Arrays.toString(parameters));

                System.out.println("MyResult = " + arrayToString(result));
                System.out.println("ExpectResult = " + arrayToString(expectResult));

                return false;
            }
        }

        return true;
    }

    public static InvokableExp executeExpression(InvokableExp exp, int N, InvokeMethodSource... sources) {
        return executeExpression(exp, N, null, sources);
    }

    public static InvokableExp executeExpression(InvokableExp exp, int N, ExecutionCallback callback, InvokeMethodSource... sources) {
        int length = sources.length;

        for (int i = 0; i < N; i++) {
            InvokeMethodSource source = sources[random.nextInt(length)];

            Object[] parameters = source.getParamsSupplier().get();

            if (callback != null) {
                callback.beforeExecute(i, exp, source, parameters);
            }

            Object result = exp.invokeMethod(source.getMethodName(), parameters);

            if (callback != null) {
                callback.afterExecute(i, exp, source, result);
            }
        }

        return exp;
    }

    public static void invokeAndExit(Runnable r) {
        invokeAndExit(true, r);
    }

    public static void invokeAndExit(boolean exit, Runnable r) {
        r.run();
        if (exit)
            System.exit(0);
    }

    public static <T> void invokeAndExit(Callable<T> callable, T expect) {
        try {
            if (!ObjectUtil.equals(callable.call(), expect))
                System.exit(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String arrayToString(int[][] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            builder.append(Arrays.toString(arr[i]));
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void printMatrix(
            Object matrix
    ) {

        int M = Array.getLength(matrix);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < M; i++) {

            Object row = Array.get(matrix, i);

            int N = Array.getLength(row);

            for (int j = 0; j < N; j++) {

                builder.append(String.format("%4s", Array.get(row, j))).append(StrUtil.SPACE);

            }

            builder.append(System.lineSeparator());

        }

        System.out.println(builder);
    }

    public static String arrayToString(Object array) {
        // 检查是否为 null
        if (array == null) {
            return "null";
        }

        // 检查是否为数组
        if (array.getClass().isArray()) {
            // 一维数组
            if (array instanceof int[]) {
                return Arrays.toString((int[]) array);
            } else if (array instanceof double[]) {
                return Arrays.toString((double[]) array);
            } else if (array instanceof boolean[]) {
                return Arrays.toString((boolean[]) array);
            } else if (array instanceof char[]) {
                return Arrays.toString((char[]) array);
            } else if (array instanceof byte[]) {
                return Arrays.toString((byte[]) array);
            } else if (array instanceof short[]) {
                return Arrays.toString((short[]) array);
            } else if (array instanceof long[]) {
                return Arrays.toString((long[]) array);
            } else {
                // 处理对象数组
                return Arrays.deepToString((Object[]) array);
            }
        } else {
            return array.toString(); // 如果不是数组，调用原生的 toString()
        }
    }

    public static String toStringWithIndex(Object array) {

        if (array == null)
            return "null";

        if (!array.getClass().isArray())
            return array.toString();

        int length = Array.getLength(array);

        StringBuilder builder = new StringBuilder();

        builder.append(
                StrUtil.wrap(
                        IntStream.range(0, length).mapToObj(value -> ((Integer) value).toString()).collect(Collectors.joining(", ")),
                        "[",
                        "]"
                )
        );

        builder.append(System.lineSeparator());

        builder.append(arrayToString(array));

        return builder.toString();
    }

}

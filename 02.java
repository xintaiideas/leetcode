import java.util.Arrays;
import java.util.Scanner;
 
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
 
    int x = sc.nextInt();
    int y = sc.nextInt();
 
    Integer[] arrX = new Integer[x];
    for (int i = 0; i < x; i++) {
      arrX[i] = sc.nextInt();
    }
 
    System.out.println(getResult(arrX, x, y));
  }
 
  public static int getResult(Integer[] arr, int x, int y) {
    // 按照正常效率降序
    Arrays.sort(arr, (a, b) -> b - a);
 
    int max = 0;
    int count = 0;
    int i;
    int j;
 
    // 如果志愿者少于采样员，则优先将志愿者分配给正常效率高的采样员
    if (y < x) {
      // 0~y-1范围内高效率的采样员优先获得一个志愿者，因此保持正常效率，而y~x-1范围内的低效率采样员则没有志愿者，效率下降20%
      for (int k = 0; k < x; k++) {
        max += k < y ? arr[k] : arr[k] * 0.8;
      }
 
      i = 0;
      j = y - 1;
    }
    // 如果志愿者 不少于 采样员，那么默认情况下每个采样员都分配一个志愿者
    else {
      // 如果志愿者人数超过采样员四倍，则多出来的志愿者就没有作用了
      if (y >= 4 * x) {
        y = 4 * x;
      }
 
      // 每个采样员都默认发挥正常效率
      for (Integer val : arr) {
        max += val;
      }
 
      // surplus记录每个采样员分配一个志愿者后，还多出来的志愿者
      int surplus = y - x;
 
      i = 0;
      j = x - 1;
 
      // 优先将多出来的志愿者分配给高效率的采样员
      while (surplus > 0) {
        max += arr[i] * 0.1;
        surplus--;
        if (++count == 3) {
          count = 0;
          i++;
        }
      }
    }
 
    // 志愿者分配完后，则继续考虑剥夺低效率采样员的志愿者给高效率的采样员
    while (i < j) {
      // 如果最高效率的采样员上升10%的效率  是否大于  最低效率的采样员下降20%的效率，那么就值得剥夺
      if (arr[i] * 0.1 > arr[j] * 0.2) {
        max += arr[i] * 0.1 - arr[j] * 0.2;
 
        // 由于一个采样员最多只能提升30%，即除了一个基础志愿者外，最多再配3个志愿者，多配了也没用
        if (++count == 3) {
          count = 0;
          i++;
        }
        j--;
      } else {
        break;
      }
    }
 
    return max;
  }
}
import java.util.Scanner;

public class QuadraticCongruences {
	public static int a;
	public static int b;
	public static int c;
	public static int p;

	public static int fermat(int base, int power) {
		int result = 1;
		base = mod(base);
		while (power > 0) {
			if ((power & 1) == 1)
				result = mod((result * base));
			power = power >> 1;
			base = mod((base * base));
		}
		return result;
	}

	public static boolean isOddPrime(int n) {
		if ((n % 2 != 0) && isPrime(n))
			return true;
		else
			return false;
	}

	public static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		for (int i = 2; i < n; i++)
			if (n % i == 0)
				return false;
		return true;
	}

	public static void solution(int d) {
		int alpha = countAlpha(d);
		int n = -b + alpha;
		int n2 = -b - alpha;
		n = mod(n);
		n2 = mod(n2);
		int solution1 = (n) * modInverse(2 * a, p);
		int solution2 = (n2) * modInverse(2 * a, p);
		solution1 = mod(solution1);
		solution2 = mod(solution2);
		System.out.println("x = {" + solution1 + "," + solution2 + "}");
	}

	public static int countAlpha(int d) {
		int k = 0;
		boolean perfectSquare = isPerfectSquare(d + (p * k));
		while (!perfectSquare) {
			k = k + 1;
			perfectSquare = isPerfectSquare(d + (p * k));
		}
		double alpha = Math.sqrt(d + (p * k));
		return (int) alpha;
	}

	public static boolean isPerfectSquare(double x) {
		double sr = Math.sqrt(x);
		return ((sr - Math.floor(sr)) == 0);
	}

	public static int modInverse(int a, int prime) {
		a = mod(a);
		for (int x = 1; x < prime; x++)
			if (mod((a * x)) == 1)
				return x;
		return -1;
	}

	public static int mod(int n) {
		int result = n % p;
		if (result < 0) {
			if (p < 0) {
				result = result - p;
			} else {
				result = result + p;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int choice = 1;
		Scanner input = new Scanner(System.in);
		while (choice >= 0) {
			System.out.print("Enter the value of a: ");
			a = input.nextInt();
			System.out.print("Enter the value of b: ");
			b = input.nextInt();
			System.out.print("Enter the value of c: ");
			c = input.nextInt();
			System.out.print("Enter the value of p (must be odd prime) : ");
			p = input.nextInt();
			System.out.println();
			int result = mod(a);
			if ((result == 0) || (!isOddPrime(p))) {
				System.out.println("NO SOLUTION becasue a not divisible by p or p is not odd prime please try again");
				break;
			}
			int d = (b * b) - 4 * a * c;
			int power = (p - 1) / 2;
			int base = d;
			if (fermat(base, power) == 1) {
				solution(d);
			} else
				System.out.println("NO SOLUTION");

			System.out.println("to try again enter any postive number or negative number to exist ");
			System.out.println();
			choice = input.nextInt();
		}
		System.out.println("GoodBye!");
	}
}
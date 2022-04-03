import java.util.Scanner;

public class Prak11_1 {

	public static int[] doInsertionSort(int[] arrDiurutkan) {
		int putaran, index, temp;
		int panjangArr = arrDiurutkan.length;
		for (putaran = 1; putaran < panjangArr; putaran++) {
			temp = arrDiurutkan[putaran];
			index = putaran - 1;
			while (index >= 0 && temp < arrDiurutkan[index]) {
				arrDiurutkan[index + 1] = arrDiurutkan[index];
				index = index - 1;
			}
			arrDiurutkan[index + 1] = temp;
		}
		return arrDiurutkan;
	}
	
	public static int cariSequential(int[] arrData, int keyDicari) {
		// penelusuran satu per satu
		for (int i = 0; i < arrData.length; i++) {
			// mencocokkan
			if (arrData[i] == keyDicari) {
				return i;
			}
		}
		return -1;
	}

	public static int cariBinary(int[] arrData, int keyDicari) {
		int[] arrDataUrut = doInsertionSort(arrData);
		int mid, low, high, putaran;
		low = 0;
		putaran = 0;
		high = (arrDataUrut.length) - 1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (arrDataUrut[mid] == keyDicari) {
				return putaran;
			} else if (arrDataUrut[mid] > keyDicari) {
				high = mid - 1;
				putaran++;
			} else{
				low = mid + 1;
				putaran++;
			}
		}
		return -1;
	}

	public static int cariInterpolation(int[] arrData, int keyDicari) {
		int[] arrDataUrut = doInsertionSort(arrData);
		int low, high, pos, putaran;
		low = 0;
		putaran = 0;
		high = arrDataUrut.length - 1;
		do {
			pos = (keyDicari - arrDataUrut[low]) * (high - low) 
					/ (arrDataUrut[high] - arrDataUrut[low]) + low;
			if(pos >= 0 && pos <= arrDataUrut.length) {
				if (arrDataUrut[pos] == keyDicari) {
					return putaran;
				} else if (arrDataUrut[pos] > keyDicari) {
					high = pos - 1;
				} else {
					low = pos + 1;
				}
				putaran++;
			}
		} while (keyDicari >= arrDataUrut[low] && keyDicari <= arrDataUrut[high]);
		return -1;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Masukkan jumlah data : ");
		int length = scan.nextInt();
		System.out.print("Masukkan isi data : ");
		int[] myData = new int[length];
		for (int i = 0; i < myData.length; i++) {
			myData[i] = scan.nextInt();
		}
		System.out.print("Data yang dicari : ");
		int bilanganDicari = scan.nextInt();
		System.out.println("---------------------------------");
		boolean ulang;
		while (ulang = true) {
			System.out.println("1. Sequential\n2. Binary\n3. Interpolation\n4. Abort System !!!");
			System.out.print("Pilih jenis pencari : ");
			int pilihan = scan.nextInt();
			System.out.println("---------------------------------");
			if (pilihan == 1) {
				int indexBilanganDicari = cariSequential(myData, bilanganDicari);
				if(indexBilanganDicari >= 0) {
					System.out.println("Data ditemukan");
					System.out.printf("Data berada di index ke : %d\n", indexBilanganDicari);
					System.out.printf("Dengan %d putaran\n", indexBilanganDicari + 1);
				}else 	System.out.println("Data tidak ditemukan");
			} else if (pilihan == 2) {
				int putaranBinary = cariBinary(myData, bilanganDicari);
				if(putaranBinary >= 0) {
					System.out.println("Data ditemukan");
					System.out.printf("Dengan %d putaran\n", putaranBinary);
				}else 	System.out.println("Data tidak ditemukan");
			} else if (pilihan == 3) {
				int putaranInterpolation = cariInterpolation(myData, bilanganDicari);
				if(putaranInterpolation >= 0) {
				System.out.println("Data ditemukan");
				System.out.printf("Dengan %d putaran\n", putaranInterpolation);
				}else 	System.out.println("Data tidak ditemukan");
			} else if (pilihan == 4) {
				ulang = false;
				break;
			}
			System.out.println("---------------------------------");
		}
		System.out.println("Sampai ketemu lagi !!!");
	}
}


/*Alihan Demirel

 */
import java.util.Iterator;

public class CrazyMarket implements MyQueue<Customer> {
	private int n;
	private Node front;
	private Node rear;

	public class Node {
		Node next;
		Customer data;

		public Node(Customer data) {
			this.data = data;
		}
	}

	public CrazyMarket() {
		this.front = null;
		this.rear = null;
		n = 0;
	}

	static String tekerleme = "O piti piti karamela sepeti " + "\nTerazi lastik jimnastik "
			+ "\nBiz size geldik bitlendik Hamama gittik temizlendik.";
	String sesliler = "aeıioöuüAEIİOÖUÜ";

	public CrazyMarket(int numberOfCustomer) {

		new CrazyMarket(numberOfCustomer, tekerleme);

	}

	public CrazyMarket(int numberOfCustomer, String tekerleme) {

		double array_arrival[] = new double[1000];
		double array_hizmet[] = new double[1000];
		int i = 1;
		Customer person = new Customer();
		double serviceTime;
		for (int j = 1; j < numberOfCustomer + 1; j++) {

			enqueue(person);
			person.arrivalTime = Math.random() * 2;
			person.removalTime = (1 + Math.random() * 2);
			System.out.println(j + " .kisinin arrival time:" + person.arrivalTime);
			System.out.println(j + " .kisinin islem suresi:" + person.removalTime);
			System.out.println("\n");

		}
		System.out.println("\n");
		while (numberOfCustomer != 0) {

			array_arrival[0] = 0;
			array_hizmet[0] = 0;
			array_arrival[i] = person.arrivalTime + array_arrival[i - 1];

			array_hizmet[i] = person.removalTime + array_hizmet[i - 1];

			serviceTime = array_hizmet[i] - array_arrival[i + 1];

			System.out.println(i + ".kisinin bekleme suresi " + serviceTime);

			if (serviceTime > 10) {
				System.out.println("Bekleme suresi 10 dan buyuk oldugu icin en bastaki kisi kuyruktan cikti");
				System.out.println("\n");
				dequeuNext();
			} else {

				dequeuWithCounting(tekerleme);
			}
			numberOfCustomer--;
			i++;
		}

	}

	// kuyrugun boyutunu belirtir
	public int size() {
		return n;
	}

	// kuyrugun bos olup olmadigina bakar
	public boolean isEmpty() {
		if (n == 0) {
			return true;
		}
		return false;
	}

	// kuyrugun sonuna item ekler
	public boolean enqueue(Customer item) {
		Node newNode = new Node(item);
		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			rear.next = newNode;
		}
		rear = newNode;
		n++;
		return true;
	}

	// kuyrugun basindan eleman cikarir
	public Customer dequeuNext() {
		Customer temp = front.data;

		if (isEmpty()) {
			rear = null;

		}
		front = front.next;
		n--;
		return temp;
	}

	// tekerlemedeki sesli harf sayisini bulur
	public int find_position(String tekerleme) {
		int count = 0;
		int i;
		for (i = 0; i < tekerleme.length(); i++) {
			for (int j = 0; j < sesliler.length(); j++) {
				if (tekerleme.charAt(i) == sesliler.charAt(j)) {
					count++;
				}
			}
		}
		return count;
	}

	// tekerleme metnini kullanarak bir sonraki elemani secer ve cikartir
	public Customer dequeuWithCounting(String tekerleme) {
		int position = find_position(tekerleme);
		Customer x;

		Node temp = front;

		if (position == 0) {
			front = temp.next;
			x = front.data;
			return x;

		}
		if (position > size()) {

			position = (position % size());

		}
		x = front.data;

		for (int i = 0; i < position - 1; i++) {

			temp = temp.next;
		}

		Node next = temp.next.next;

		temp.next = next;
		System.out.println((position + 1) + ".kisi kuyruktan cikti");
		System.out.println("\n");
		n--;
		return x;
	}

	// iteratörler
	public Iterator<Customer> iterator() {
		return new StackIterator();
	}

	private class StackIterator implements Iterator<Customer> {
		private Node itr = front;

		public boolean hasNext() {
			return itr != null;
		}

		public Customer next() {
			Customer data = itr.data;
			itr = itr.next;
			return data;
		}
	}

}

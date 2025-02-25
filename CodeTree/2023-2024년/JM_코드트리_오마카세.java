import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 28%에서 틀렸던 이유: 사람이 나가는 시간을 MAX로 Update 안해줌
 */
public class JM_코드트리_오마카세 {
	static class Sushi {
		int time;
		int pos;
		String name;

		public Sushi(int time, int pos, String name) {
			this.time = time;
			this.pos = pos;
			this.name = name;
		}
	}
	static class Person {
		int time;
		int pos;
		String name;
		int n;
		int maxOutTime;
		public Person(int time, int pos, String name, int n) {
			this.time = time;
			this.pos = pos;
			this.name = name;
			this.n = n;
		}
	}
	static class Time implements Comparable<Time> {
		int time;
		char command;
		boolean isOutTime;

		public Time(int time, char command, boolean isOutTime) {
			this.time = time;
			this.command = command;
			this.isOutTime = isOutTime;
		}

		@Override
		public int compareTo(Time o) {
			if(this.time == o.time){
				if (this.command == 'T' && o.command != 'T') return 1;
				if (this.command != 'T' && o.command == 'T') return -1;
				return 0;
			}
			return this.time - o.time;
		}

		@Override
		public String toString() {
			return "Time{" +
				"time=" + time +
				", command=" + command +
				", isOutTime=" + isOutTime +
				'}';
		}
	}
	static int L; // 초밥 벨트의 길이
	static int Q; // 명령의 수
	static Map<String, Person> persons;
	static List<Sushi> sushi;
	static List<Time> times;

	private static List<Time> findOutTime() {
		for(Sushi s: sushi) {
			Person person = persons.get(s.name);
			int outTime = s.time;
			int outPos = s.pos;

			if(s.time < person.time) {	// 스시가 먼저 도착한 경우
				outPos = ((person.time - s.time) + s.pos) % L; // 사람의 시간과 스시 시간 맞춰서 이동
				outTime = person.time;
			}

			if(outPos <= person.pos) outTime += (person.pos - outPos);
			else if(outPos > person.pos) outTime += L - (outPos - person.pos);

			times.add(new Time(outTime, 'S', true));
			person.n--;
			person.maxOutTime = Math.max(person.maxOutTime, outTime);
			if(person.n == 0) times.add(new Time(person.maxOutTime, 'P', true));
		}
		return times;
	}

	public static String solve() {
		StringBuilder sb = new StringBuilder();

		findOutTime();
		Collections.sort(times);

		int sushiCount = 0;
		int personCount = 0;
		for(Time t : times) {
			switch (t.command) {
				case 'T':
					sb.append(personCount).append(" ")
						.append(sushiCount).append("\n");
					break;
				case 'P':
					if(t.isOutTime) personCount--;
					else personCount++;
					break;
				case 'S':
					if(t.isOutTime) sushiCount--;
					else sushiCount++;
					break;
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		persons = new HashMap<>();
		sushi = new ArrayList<>();
		times = new ArrayList<>();

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if(command == 100){
				int t = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				String name = st.nextToken();
				sushi.add(new Sushi(t, x, name));
				times.add(new Time(t, 'S', false));
			}
			else if (command == 200) {
				int t = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				String name = st.nextToken();
				int n = Integer.parseInt(st.nextToken());
				persons.put(name, new Person(t, x, name, n));
				times.add(new Time(t, 'P', false));
			}
			else {
				int t = Integer.parseInt(st.nextToken());
				times.add(new Time(t, 'T', false));
			}
		}
		System.out.println(solve());
	}
}

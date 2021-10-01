package mini;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class wav {
	// 로그인 성공
	public void haha() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\haha.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}

	// 종료
	public void bye() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\bye.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}

	// 취업성공
	public void success() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\success.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}

	// 숫자 선택 음
	public void num() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\num.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}
	// 욕구

	public void food() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\food.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}

	public void sleep() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\sleep.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}

	public void rest() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\rest.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}

	public void study() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\study.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}

	public void sick() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\sick.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}

	// 욕구 실패
	public void fail() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\fail.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}

	// 욕구 성공
	public void yes() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\yes.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}

	// 탄생
	public void birth() throws Exception {
		File a = new File("C:\\Users\\smhrd\\Desktop\\wav\\birth.wav");
		AudioInputStream b = AudioSystem.getAudioInputStream(a);
		Clip c = AudioSystem.getClip();

		c.open(b);
		c.start();
		Thread.sleep(c.getMicrosecondLength() / 2000);
	}
}
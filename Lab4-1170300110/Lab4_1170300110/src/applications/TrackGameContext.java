package applications;

public class TrackGameContext {
	
	public TrackGame game = new TrackGameRandom();
	
	public void run(TrackGame g)  {
		//������Ϸ���Ͳ�ͬ random sorted
		game = g;
		
		game.setPlayer();
	}
	
}

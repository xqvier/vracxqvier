package {
	import flash.display.MovieClip;
		
	import flash.events.MouseEvent;
	
	public class Main extends MovieClip {
		private var playlist:Playlist;
		private var playin:Boolean;
		public function Main() {
			this.addChild(playButton);
			playButton.x = 20;
			playButton.y = 20;
			playButton.addEventListener(MouseEvent.CLICK, goPause);
			playlist = new Playlist();
			var son:Son = new Son("sounds/possible_future.mp3");
			
			playlist.add(son);
			
		}
		private function goPause(event:MouseEvent){
			if(playin){
				playlist.pause();
			} else {
				playlist.play();
			}
		}
	}
}
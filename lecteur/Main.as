package {
	import flash.display.MovieClip;
		
	import flash.events.MouseEvent;
	import flash.geom.Rectangle;
	import flash.events.Event;
	
	public class Main extends MovieClip {
		private var playlist:Playlist;
		private var playin:Boolean = false;
		
		public function Main() {
			this.addChild(playButton);
			this.addChild(stopButton);
			this.addChild(volume);
			volume.addChild(volume.volumeBar);
			volume.addChild(volume.volumeSpinner);
			playButton.x = 20;
			playButton.y = 20;
			stopButton.x = 100;
			stopButton.y = 20;
			volume.x = 200;
			volume.y = 20;
			
			playButton.addEventListener(MouseEvent.CLICK, goPause);
			stopButton.addEventListener(MouseEvent.CLICK, goStop);
			volume.addEventListener(MouseEvent.MOUSE_DOWN, manageVolume);
			stage.addEventListener(MouseEvent.MOUSE_UP, stopDr);
			stage.addEventListener(Event.ENTER_FRAME, enter_frame);
			
			playlist = new Playlist();
			
		}
		private function goPause(event:MouseEvent){
			if(playin){
				playlist.pause();
				playin = false;
			} else {
				playlist.play();
				playin = true;
			}
		}
		
		private function goStop(event:MouseEvent){
			playlist.stop();
			playin = false;
		}
		
		private function manageVolume(event:MouseEvent){
			var posx:int = volume.volumeBar.x;
			var posy:int = volume.volumeBar.y-volume.volumeSpinner.height+volume.volumeBar.height;
			var w:int = volume.volumeBar.width-volume.volumeSpinner.width;
			var h:int = volume.volumeBar.height;
			var rect:Rectangle = new Rectangle(posx,posy,w,h);
			volume.volumeSpinner.startDrag(false,rect);
		}
		
		private function stopDr(event:MouseEvent){
			volume.volumeSpinner.stopDrag();
		}
		private function enter_frame(event:Event){
			if(playin){
				var vol:int = (volume.volumeSpinner.x - volume.volumeBar.x) * 100 / (volume.volumeBar.width-volume.volumeSpinner.width);
				playlist.setVolume(vol);
			}
		}
	}
}
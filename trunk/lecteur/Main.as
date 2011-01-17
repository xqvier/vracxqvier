package {
	import flash.display.MovieClip;
		
	import flash.events.MouseEvent;
	import flash.geom.Rectangle;
	import flash.events.Event;
	import flash.text.TextField;
	
	public class Main extends MovieClip {
		private var playlist:Playlist;
		
		public function Main() {			
			playlist = new Playlist();
			
			this.addChild(playButton);
			this.addChild(stopButton);
			this.addChild(volume);
			volume.addChild(volume.volumeBar);
			volume.addChild(volume.volumeSpinner);
			this.addChild(prevButton);
			this.addChild(nextButton);
			this.addChild(playlist);
			
			playButton.x = 50;
			playButton.y = 20;
			stopButton.x = 100;
			stopButton.y = 20;
			volume.x = 200;
			volume.y = 20;
			prevButton.x = 0;
			prevButton.y = 20;
			nextButton.x = 150;
			nextButton.y = 20;
			
			playButton.height = 20;
			stopButton.height = 20;
			prevButton.height = 20;
			nextButton.height = 20;
			playButton.width = 20;
			stopButton.width = 20;
			prevButton.width = 20;
			nextButton.width = 20;
			volume.height = 30;
			volume.width = 100;
//			playlist.width = stage.stageWidth;
//			playlist.height = 300;
			
			playButton.addEventListener(MouseEvent.CLICK, goPause);
			stopButton.addEventListener(MouseEvent.CLICK, goStop);
			prevButton.addEventListener(MouseEvent.CLICK, goPrev);
			nextButton.addEventListener(MouseEvent.CLICK, goNext);
			volume.volumeSpinner.addEventListener(MouseEvent.MOUSE_DOWN, manageVolume);
			volume.volumeBar.addEventListener(MouseEvent.CLICK, setVolume)
			this.addEventListener(MouseEvent.MOUSE_UP, stopDr);
			
			
		}
		
		private function goPause(event:MouseEvent){
			playlist.goPause();
		}
		
		private function goStop(event:MouseEvent){
			playlist.goStop();
		}
		private function goPrev(event:MouseEvent){
			playlist.goPrev();
		}
		private function goNext(event:MouseEvent){
			playlist.goNext();
		}
		
		private function manageVolume(event:MouseEvent){
			var posx:int = volume.volumeBar.x;
			var posy:int = volume.volumeBar.y-((volume.volumeSpinner.height-volume.volumeBar.height)/2);
			var w:int = volume.volumeBar.width-volume.volumeSpinner.width;
			var h:int = 0;
			var rect:Rectangle = new Rectangle(posx,posy,w,h);
			volume.volumeSpinner.startDrag(false,rect);
			this.addEventListener(Event.ENTER_FRAME, enter_frame);
		}
		private function setVolume(event:MouseEvent):void {
			if(event.stageX > volume.x + volume.volumeBar.width - volume.volumeSpinner.width){
				volume.volumeSpinner.x = volume.volumeBar.width - volume.volumeSpinner.width;
			} else {
				volume.volumeSpinner.x = event.stageX-volume.x;
			}
		}
		private function stopDr(event:MouseEvent){
			volume.volumeSpinner.stopDrag();
			this.removeEventListener(Event.ENTER_FRAME, enter_frame);
		}
		private function enter_frame(event:Event){
			if(this.playlist.isPlayin()){
				var vol:int = (volume.volumeSpinner.x - volume.volumeBar.x) * 100 / (volume.volumeBar.width-volume.volumeSpinner.width);
				//playlist.setVolume(vol);
			}
		}
	}
}
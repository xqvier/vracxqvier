package {
	import flash.display.MovieClip;
		
	import flash.events.MouseEvent;
	import flash.geom.Rectangle;
	import flash.events.Event;
	import flash.text.TextField;
	
	public class Main extends MovieClip {
		private var playlist:Playlist;
		private var playin:Boolean = false;
		
		public function Main() {
			this.addChild(playButton);
			this.addChild(stopButton);
			this.addChild(volume);
			volume.addChild(volume.volumeBar);
			volume.addChild(volume.volumeSpinner);
			this.addChild(prevButton);
			this.addChild(nextButton);
			
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
			
			playButton.addEventListener(MouseEvent.CLICK, goPause);
			stopButton.addEventListener(MouseEvent.CLICK, goStop);
			prevButton.addEventListener(MouseEvent.CLICK, goPrev);
			nextButton.addEventListener(MouseEvent.CLICK, goNext);
			volume.volumeSpinner.addEventListener(MouseEvent.MOUSE_DOWN, manageVolume);
			volume.volumeBar.addEventListener(MouseEvent.CLICK, setVolume)
			this.addEventListener(MouseEvent.MOUSE_UP, stopDr);
			this.addEventListener(Event.ENTER_FRAME, enter_frame);
			
			
			playlist = new Playlist();
			while(!playlist.isReady()){
				trace("not ready");
			}
			
		}
		private function playlistReady(event:Event){
			var text:TextField = playlist.text();
		
			this.addChild(text);
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
		private function goPrev(event:MouseEvent){
			playlist.prev();
		}
		private function goNext(event:MouseEvent){
			playlist.next();
		}
		
		private function manageVolume(event:MouseEvent){
			var posx:int = volume.volumeBar.x;
			var posy:int = volume.volumeBar.y-((volume.volumeSpinner.height-volume.volumeBar.height)/2);
			var w:int = volume.volumeBar.width-volume.volumeSpinner.width;
			var h:int = 0;
			var rect:Rectangle = new Rectangle(posx,posy,w,h);
			volume.volumeSpinner.startDrag(false,rect);
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
		}
		private function enter_frame(event:Event){
			if(playin){
				var vol:int = (volume.volumeSpinner.x - volume.volumeBar.x) * 100 / (volume.volumeBar.width-volume.volumeSpinner.width);
				playlist.setVolume(vol);
			}
		}
	}
}
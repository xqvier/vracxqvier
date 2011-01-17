package {
	import flash.net.URLRequest;
	import com.adobe.serialization.json.JSON;
	import flash.net.URLLoader;
	import flash.events.Event;
	import flash.text.TextField;	
	import flash.display.MovieClip;

	public class Playlist extends MovieClip{
		private var list:Array = new Array();
		private var position:int = 0;
		private var urlload:URLLoader;
		private var text:TextField;
		private var printedMed:Media;
		public function Playlist(){
			load();
		}
		
		private function load():void{
			var urlreq:URLRequest = new URLRequest("playlist.json");
			this.urlload = new URLLoader();
			this.urlload.addEventListener(Event.COMPLETE, loadComplete);
			this.urlload.load(urlreq);
			
		}
		private function loadComplete(event:Event):void{
			var o:Object = JSON.decode(urlload.data as String);
			for(var i:int = 0; i<o.playlist.length ; i++){
				if(o.playlist[i].type == "sound"){
					
					var son:Son = new Son(o.playlist[i].url, o.playlist[i].titre);
					add(son,o.playlist[i].pos);
					
				} else if (o.playlist[i].type == "video"){
					var vid:Vid = new Vid(o.playlist[i].url, o.playlist[i].titre, o.playlist[i].codec);
					add(vid,o.playlist[i].pos);
				}
			}
			print();
		}
		private function add(med:Media, pos:int):void{
			//list.addItemAt(med, pos);
			this.list.push(med);
		}
		public function del(son:Son):void{
		}
		public function goPause():void{
			if(this.isPlayin()){
				this.list[this.position].goPause();
			} else {
				this.list[this.position].goPlay();
			}
		}
		public function goStop():void{
			this.list[this.position].goStop();
			this.repaint();
		}
		public function goPrev():void{
			this.goStop();
			trace(this.list.length);			if(this.position == 0){
				this.position = this.list.length-1;
			} else {
				this.position--;
			}
			this.goPause();
			this.repaint();
		}
		public function goNext():void{
			this.goStop();
			if(this.position == this.list.length-1){
				this.position = 0;
			} else {
				this.position++;
			}
			this.goPause();
			this.repaint();
		}
		public function setVolume(vol:int):void{
			this.list[this.position].setVolume(vol);
		}
		
		private function print():void{
			this.text = new TextField();
			this.text.x = 0;
			this.text.y = 50;
			this.text.height = 300;
			this.text.width = 200;
			for(var i:int = 0;i<this.list.length;i++){
				this.text.appendText(this.list[i].getTitre());
				if(i==this.position){
					this.text.appendText(" (currently selected)");
				}
				this.text.appendText("\n");
			}
			this.addChild(this.text);
			this.printedMed = this.list[this.position];
			this.addChild(this.printedMed);
			this.printedMed.x = 200;
			this.printedMed.y = 50;
		}
		private function repaint():void{
			this.removeChild(this.text);
			this.removeChild(this.printedMed);
			this.print();
		}
		public function isPlayin():Boolean{
			return this.list[this.position].isPlayin();
		}
	}
}
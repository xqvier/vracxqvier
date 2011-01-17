package {
	import flash.net.URLRequest;
	import com.adobe.serialization.json.JSON;
	import flash.net.URLLoader;
	import flash.events.Event;
	import flash.text.TextField;
	import flash.text.TextFormat;

	public class Playlist {
		private var list:Array = new Array();
		private var position:int = 0;
		private var urlload:URLLoader;
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
			//trace(o.playlist[0].url as String);
			for(var i:int = 0; i<o.playlist.length ; i++){
				if(o.playlist[i].type == "sound"){
					
					var son:Son = new Son(o.playlist[i].url, o.playlist[i].titre);
					add(son,o.playlist[i].pos);
					
				}
			}
		}
		
		private function add(med:Media, pos:int):void{
			//list.addItemAt(med, pos);
			list.push(med);
		}
		public function play():void{
			list[this.position].play();
		}
		public function del(son:Son):void{
		}
		public function pause():void{
			list[this.position].pause();
		}
		public function stop():void{
			list[position].stop();
		}
		public function prev():void{
			this.stop();
			if(this.position == 0){
				this.position = this.list.length-1;
			} else {
				this.position--;
			}
			this.play();
		}
		public function next():void{
			this.stop();
			if(this.position == this.list.length-1){
				this.position = 0;
			} else {
				this.position++;
			}
			this.play();
		}
		public function setVolume(vol:int):void{
			this.list[this.position].setVolume(vol);
		}
		
		public function toString():TextField{
			var text:TextField = new TextField();
			text.x = 0;
			text.y = 50;
			text.setTextFormat(new TextFormat());
			text.text = "SALUT";
			trace(this.list.length);
			for(var i:int = 0;i<this.list.length;i++){
				text.appendText(this.list[i].getTitre()+"\n");
				trace(this.list[i].getTitre()+"\n");
			}
			return text;
		}
	}
}
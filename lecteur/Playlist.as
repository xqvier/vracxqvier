package {
	import flash.net.URLRequest;
	import com.adobe.serialization.json.JSON;
	import flash.net.URLLoader;
	import flash.events.Event;

	public class Playlist {
		private var list
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
					
					var son:Son = new Son(o.playlist[i].url);
					add(son,o.playlist[i].pos);
					
				}
			}
			playlist.sort();
		}
		
		private function add(med:Media, pos:int):void{
			list.addItemAt(med, pos);
		}
		public function play():void{
			list[position].play();
		}
		public function del(son:Son):void{
		}
		public function pause():void{
			list[position].pause();
		}
		public function stop():void{
			list[position].stop();
		}
		public function setVolume(vol:int):void{
			list[position].setVolume(vol);
		}
	}
}
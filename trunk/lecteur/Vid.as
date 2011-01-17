package{
	import flash.media.Video;
	
	import flash.net.NetStream;
	import flash.net.NetConnection;
	import flash.media.SoundTransform;
	import flash.text.TextField;	
	
	import flash.events.NetStatusEvent;
	
	public class Vid extends Media {
		private var video:Video;
		private var netStream:NetStream;
		private var transf:SoundTransform;
		private var url:String;
		private var codec:String;
		private var playin:Boolean = false;
		private var temp:TextField;
		
		public function Vid(url:String, titre:String, codec:String):void{
			super(titre);
			this.url = url;
			this.codec = codec;
			
			var netConnect:NetConnection = new NetConnection();
			netConnect.connect(null);
			this.netStream = new NetStream(netConnect);
			this.netStream.client = this;
			this.video = new Video();
			this.video.attachNetStream(netStream);
			this.transf = new SoundTransform();
			
			this.temp = new TextField();
			this.temp.text = this.titre;
		}
		
		public function goPlay(){
			if(this.pos>0){
				this.netStream.resume();
			} else {
				this.netStream.play(this.url);
			}
			this.addChild(this.video);
			this.playin = true;
		}
		public function goPause(){
			this.netStream.pause();
			this.pos = this.getTime();
			this.playin = false;
		}
		public function goStop(){
			this.netStream.close();
			this.playin = false
			this.removeChild(this.video);
		}
		public function setVolume(vol:int):void{
			var x:Number = vol/100;
			this.transf.volume = x;
			this.netStream.soundTransform = this.transf;
		}
		public function getTime():Number{
			return this.netStream.time;
		}
		public function isPlayin():Boolean{
			return this.playin;
		}
		public function onMetaData(i:Object){
		}
	}
	
}
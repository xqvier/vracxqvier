package {
	import flash.media.SoundChannel;
	import flash.media.Sound;
	import flash.media.SoundTransform;
	import flash.media.SoundMixer;
	import flash.utils.ByteArray;
	import flash.display.Graphics;
	import flash.events.Event;
	
	import flash.net.URLRequest;
	import flash.text.TextField;	
	
	public class Son extends Media{
		private var son:Sound;
		private var channel:SoundChannel;
		private var transf:SoundTransform;
		private var playin:Boolean = false;
		private var mixer:SoundMixer;
		
		public function Son(url:String, titre:String){
			super(titre);
			var urlRequest:URLRequest = new URLRequest(url);
			this.son = new Sound();
			this.son.load(urlRequest);
			
			this.transf = new SoundTransform();
		}
		public function goPlay(){
			this.channel = this.son.play(this.pos);
			this.channel.soundTransform = transf;
			this.playin = true;
			this.addEventListener(Event.ENTER_FRAME, onEnterFrame);
		}
		public function goPause(){
			if(this.isPlayin()){
				this.channel.stop();
				this.removeEventListener(Event.ENTER_FRAME, onEnterFrame);
			} 
			this.pos = this.channel.position;
			this.playin = false
		}
		public function goStop(){
			if(this.isPlayin()){
				this.channel.stop();
				this.removeEventListener(Event.ENTER_FRAME, onEnterFrame);
			} 
			this.pos = 0;
			this.playin = false;
		}
		public function setVolume(vol:int):void{
			var x:Number = vol/100;
			this.transf.volume = x;
			this.channel.soundTransform = this.transf;
		}
		public function isPlayin():Boolean{
			return this.playin;
		}
		
        private function onEnterFrame(event:Event):void {
            var bytes:ByteArray = new ByteArray();
            const PLOT_HEIGHT:int = 200;
            const CHANNEL_LENGTH:int = 256;

            SoundMixer.computeSpectrum(bytes, false, 0);
            
            var g:Graphics = this.graphics;
            
            g.clear();
       
            g.lineStyle(0, 0x6600CC);
            g.beginFill(0x6600CC);
            g.moveTo(0, PLOT_HEIGHT);
            
            var n:Number = 0;
            
            for (var i:int = 0; i < CHANNEL_LENGTH; i++) {
                n = (bytes.readFloat() * PLOT_HEIGHT);
                g.lineTo(i * 2, PLOT_HEIGHT - n);
            }

            g.lineTo(CHANNEL_LENGTH * 2, PLOT_HEIGHT);
            g.endFill();
 
            g.lineStyle(0, 0xCC0066);
            g.beginFill(0xCC0066, 0.5);
            g.moveTo(CHANNEL_LENGTH * 2, PLOT_HEIGHT);
            
            for (i = CHANNEL_LENGTH; i > 0; i--) {
                n = (bytes.readFloat() * PLOT_HEIGHT);
                g.lineTo(i * 2, PLOT_HEIGHT - n);
            }
  
            g.lineTo(0, PLOT_HEIGHT);
            g.endFill();
        }

	}
}
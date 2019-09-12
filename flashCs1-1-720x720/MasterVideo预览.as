package 
{
	import flash.display.MovieClip;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.external.ExternalInterface;
	import flash.media.Sound;
	import flash.media.SoundChannel;
	import flash.media.SoundLoaderContext;
	import flash.net.URLRequest;
	import flash.system.Security;
	
	import fl.transitions.Transition;
	import fl.transitions.TransitionManager;
	import fl.transitions.Tween;
	import fl.transitions.TweenEvent;
	
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.text.TextFieldAutoSize;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	
	[SWF(width=400,height=400)]
	public class MasterVideo extends Sprite
	{
		private var maxHeight:int = stage.stageHeight;
		private var maxWidth:int = stage.stageWidth;
		
		private var music:Sound; 
		private var soundChannel:SoundChannel;
		private var soundPosition:int;
		private var templet:int = 1;
		private var pictures:Array = new Array();
		private var textsArr:Array = new Array();
		private var textColor:String = "FF1CAE"; // 默认粉色
		private var mcs:Array = new Array();
		private var currentMc:int = 0;
		private var currentTween:Tween;
		private var tf:TextField;
		
		public function MasterVideo()
		{
			
			if (ExternalInterface.available) {
				try {
					flash.system.Security.allowDomain("sameDomain");
					//对JavaScript开放方法;
					ExternalInterface.addCallback("setPicUrl", setPicUrl);
					ExternalInterface.addCallback("setPicUrls", setPicUrls);
					ExternalInterface.addCallback("setMusic", setMusic);
					ExternalInterface.addCallback("setTemplet", setTemplet);
					ExternalInterface.addCallback("startVideo", startVideo);
					ExternalInterface.addCallback("resumeVideo", resumeVideo);
					ExternalInterface.addCallback("stopVideo", stopVideo);
					ExternalInterface.addCallback("setTexts", setTexts);
					ExternalInterface.addCallback("setTextsColor", setTextsColor);
					initVideoData();
				} catch (error:SecurityError) {
				} catch (error:Error) {
				}
				
			} else {
			}
		}
		
		private function initVideoData():void{
			ExternalInterface.call("initVideoData","高："+maxHeight+";宽："+maxWidth);       
		}
		
		private function callFinish():void{
			ExternalInterface.call("callFinish");       
		}
		
		/**
		*前端js设置插入文字
		*/
		private function setTexts(texts:String):void{
			textsArr = texts.split(",");
		}
		/**
		* 设置描述文字的颜色
		*/
		private function setTextsColor(colorStr:String):void{
			textColor = colorStr;
		}
		/**
		 * 前端js设置视频的单个图片地址
		 */
		private function setPicUrl(picJs:String,index:int):void {
			var pic:Picture = new Picture(maxWidth,maxHeight,picJs);
			pictures[index] = pic;
			addChild(pictures[0]);
		}
		/**
		 * 前端js设置视频的图片地址
		 */
		private function setPicUrls(picsJs:String):void {
			var picUrls:Array = picsJs.split(",");
			var len:uint = picUrls.length; 
			var firstPicWidth:int = 0;
			for (var i:uint = 0; i < len; i++) {
				var pic:Picture = new Picture(maxWidth,maxHeight,picUrls[i]);
				pictures[i] = pic;
			}
			addChild(pictures[0]);
		}
		
		/**
		 * 前端js设置视频的音频地址
		 */
		private function setMusic(musicJs:String):void {
			var req:URLRequest = new URLRequest(musicJs); 
			var context:SoundLoaderContext = new SoundLoaderContext(1000, true); 
			music = new Sound();
			music.load(req, context); 
		}
		
		/**
		 * 前端js设置视频的转场效果模板
		 */
		private function setTemplet(templetJs:int):void {
			templet = templetJs;
			var len:uint = pictures.length; 
			for (var i:uint = 0; i < len; i++) {
				var mc:MovieClip = new MovieClip(); 
				mcs[i] = mc;
			}
		}
		
		private function createCustomTextField(textStr:String):TextField {
			
            var result:TextField = new TextField();
			switch (currentMc) {
			case 0:
				result.x = 0;
				result.y = 20;
				break;
			case 1:
				result.x = 400;
				result.y = 20;
				break;
			case 2:
				result.x = 0;
				result.y = 300;
				break;
			case 3:
				result.x = 350;
				result.y = 300;
				break;
			case 4:
				result.x = (400-result.textWidth)/2;
				result.y = 20;
				break;
			default:
				result.x = 0;
				result.y = 0;
			}

			result.autoSize = TextFieldAutoSize.LEFT;
            result.width = result.textWidth+4;
            result.height = result.textHeight+4;
			result.background = false;
			
			//设置文字样式
			var format:TextFormat = new TextFormat();
            format.font = "Verdana";
            format.color = parseInt(textColor,16);
            format.size = 30;
            format.underline = false;
			result.setTextFormat(format);
			
			var T:Timer=new Timer(10);
			T.addEventListener(TimerEvent.TIMER,showTexts);
			T.start();
			
			var i =0;
			function showTexts(e):void
			{
				result.setTextFormat(format);
				if(currentMc==1){
					result.text=textStr;
					result.setTextFormat(format);
					if(result.x<350-result.textWidth){
						result.x +=25;
					}
				}
				if(currentMc==2){
					result.text=textStr;
					result.setTextFormat(format);
					if(result.x>70){
						result.x -=25;
					}
				}
				if(currentMc==3){
					result.text=textStr;
					result.setTextFormat(format);
					if(result.x<350-result.textWidth){
						result.x +=25;
					}
				}
				if(currentMc==4){
					result.text=textStr;
					result.setTextFormat(format);
					if(result.x>70){
						result.x -=25;
					}
				}
				if(currentMc==5){
					result.x = (400-result.textWidth)/2;
					result.y = 20;
					result.appendText(textStr.charAt(i));
   					i++;
				}
   				addChild(result);
			}
            return result;
        }
		
		private function startVideo():void{
			var len:uint = mcs.length; 
			currentMc = 0;
			soundPosition = 0;
			for(var i:uint = 0;i<len;i++){
				mcs[i].x=0;
				mcs[i].y=0;
			}
			doShowPic();
			soundChannel = music.play(soundPosition);
		}
		
		private function resumeVideo():void{
			if(currentTween.isPlaying){
				currentTween.stop();
				soundPosition = soundChannel.position;
				soundChannel.stop();
			}else{
				currentTween.resume();
				soundChannel = music.play(soundPosition);
			}
		}
		
		private function stopVideo():void{
			if(soundChannel != null)
				soundChannel.stop();
			if(currentTween != null)
				currentTween.stop();
			addChild(pictures[0]);
			currentMc = 0;
			soundPosition = 0;
		}
		private function moveText():void{  //创建文本框
			//测试文字，开始
			if(textsArr[currentMc]){
				tf = createCustomTextField(textsArr[currentMc]);
				addChild(tf);
			}
		}
		
		private function doShowPic():void{
			var len:uint = pictures.length; 
			if(currentMc < len){
				mcs[currentMc].addChild(pictures[currentMc]);
				addChild(mcs[currentMc]);
				var myTween:Tween = new MyTween(mcs[currentMc],templet,currentMc).tween;
				moveText();
				if(len - currentMc > 1){
					myTween.addEventListener(TweenEvent.MOTION_FINISH,changePicEvent);
				}
				currentTween = myTween;
				currentMc++;
			}
		}
		
		private function doShowPicEvent(event:TweenEvent):void{
			var len:uint = pictures.length; 
			if(currentMc < len){
				mcs[currentMc].addChild(pictures[currentMc]);
				addChild(mcs[currentMc]);
				var myTween:Tween = new MyTween(mcs[currentMc],templet,currentMc).tween;
				moveText();
				if(len - currentMc > 1){
					myTween.addEventListener(TweenEvent.MOTION_FINISH,changePicEvent);
				}else{
					myTween.addEventListener(TweenEvent.MOTION_FINISH,hideTexts);
				}
				currentTween = myTween;
				currentMc++;
			}else{
				callFinish();
			}
		}
		
		private function hideTexts(event:TweenEvent):void{
			if(tf != null){
				tf.alpha = 0; //最后一张图展示结束 隐藏文本框
			}
		}
		
		private function changePicEvent(event:TweenEvent):void{
			if(tf != null){
				tf.alpha = 0; //切换图片前隐藏上一个文本框
			}
			mcs[currentMc].addChild(pictures[currentMc]);
			addChild(mcs[currentMc]);
			var tr:Transition = TransitionManager.start(mcs[currentMc],new TransitionObject(templet,currentMc).object);
			tr.tween.addEventListener(TweenEvent.MOTION_FINISH,doShowPicEvent);
			currentTween = tr.tween;
		}
	}
}
/* text_iife.js */
// 즉시 실행 함수로 텍스트 작성 및 삭제 애니메이션 구현
(function(){
  // 메인 h2 태그 내의 span 요소 선택
  const spanEl = document.querySelector("main h2 span");
  // 표시할 텍스트 배열
  const txtArr = ['Web Publisher', 'Front-End Developer', 'Web UI Designer', 'UX Designer', 'Back-End Developer'];
  let index = 0;
  let currentTxt = txtArr[index].split("");
  // 텍스트를 한 글자씩 작성하는 함수
  function writeTxt(){
    spanEl.textContent  += currentTxt.shift(); 
    if(currentTxt.length !== 0){ 
      setTimeout(writeTxt, Math.floor(Math.random() * 100));
    }else{
      currentTxt = spanEl.textContent.split("");
      setTimeout(deleteTxt, 3000);
    }
  }
  // 텍스트를 한 글자씩 삭제하는 함수
  function deleteTxt(){
    currentTxt.pop();
    spanEl.textContent = currentTxt.join("");
    if(currentTxt.length !== 0){
      setTimeout(deleteTxt, Math.floor(Math.random() * 100))
    }else{
      index = (index + 1) % txtArr.length;
      currentTxt = txtArr[index].split("");
      writeTxt();
    }
  }
  writeTxt();
})();
/* end text_iife.js */

/* scroll_request.js */
// 스크롤 발생 시 header에 active 클래스 추가/삭제
const headerEl = document.querySelector("header");
window.addEventListener('scroll', function(){
  requestAnimationFrame(scrollCheck);
});
function scrollCheck(){
  let browerScrollY = window.scrollY ? window.scrollY : window.pageYOffset;
  if(browerScrollY > 0){
    headerEl.classList.add("active");
  }else{
    headerEl.classList.remove("active");
  }
}
/* end scroll_request.js */

/* move.js */
// 애니메이션으로 스크롤 이동
const animationMove = function(selector){
  // ① 이동할 대상 요소 선택
  const targetEl = document.querySelector(selector);
  // ② 현재 브라우저의 스크롤 위치
  const browserScrollY = window.pageYOffset;
  // ③ 이동할 대상의 위치
  const targetScorllY = targetEl.getBoundingClientRect().top + browserScrollY;
  // ④ 스크롤 이동
  window.scrollTo({ top: targetScorllY, behavior: 'smooth' });
};
// 스크롤 이벤트 연결
const scollMoveEl = document.querySelectorAll("[data-animation-scroll='true']"); 
for(let i = 0; i < scollMoveEl.length; i++){
  scollMoveEl[i].addEventListener('click', function(e){
    const target = this.dataset.target;
    animationMove(target);
  });
}
/* End move.js */
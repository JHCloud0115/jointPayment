import styled from '@emotion/styled';
import DefaultLayout from "../layouts/DefaultLayout";

const MyPage = ()=>{
    return(
        <DefaultLayout title={"마이 페이지"}>
            <div>회원 정보</div>
            <div>회원 정보 수정</div>
            <div>비밀번호 변경</div>
            <div>로그아웃</div>
            <div>회원탈퇴</div>
        </DefaultLayout>
    )
}

export default MyPage

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
`;

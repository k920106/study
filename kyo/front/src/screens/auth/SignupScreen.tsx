import React, { useRef } from 'react';
import { StyleSheet, View, SafeAreaView, TextInput } from 'react-native';
import useForm from '@/hooks/useForm';
import InputField from '@/components/common/InputField';
import CustomButton from '@/components/common/CustomButton';
import { validateSignup } from '@/utils';
import useAuth from '@/hooks/queries/useAuth';
import Toast from 'react-native-toast-message';
import { errorMessages } from '@/constants';
import { ThemeMode } from '@/types';
import useThemeStore from '@/store/useThemeStore';

function SignScreen() {
    const { signupMutation, loginMutation } = useAuth();
    const passwordRef = useRef<TextInput | null>(null);
    const passwordConfirmRef = useRef<TextInput | null>(null);
    const { theme } = useThemeStore();
    const styles = styling(theme);

    const signup = useForm({
        initialValue: { email: '', password: '', passwordConfirm: '' },
        validate: validateSignup,
    });

    const handleSubmit = () => {
        const { email, password } = signup.values;

        signupMutation.mutate(
            { email, password },
            {
                onSuccess: () => loginMutation.mutate({ email, password }),
                onError: error =>
                    Toast.show({
                        type: 'error',
                        text1: error.response?.data.message || errorMessages.UNEXPECT_ERROR,
                        position: 'bottom',
                        visibilityTime: 2000,
                    }),
            },
        );
    };

    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.inputContainer}>
                <InputField placeholder="이메일"
                    autoFocus
                    error={signup.errors.email}
                    touched={signup.touched.email}
                    inputMode="email"
                    returnKeyType='next'
                    blurOnSubmit={false}
                    onSubmitEditing={() => passwordRef.current?.focus()}
                    {...signup.getTextInputProps('email')} />
                <InputField placeholder="비밀번호"
                    error={signup.errors.password}
                    touched={signup.touched.password}
                    secureTextEntry
                    returnKeyType='next'
                    blurOnSubmit={false}
                    ref={passwordRef}
                    onSubmitEditing={() => passwordConfirmRef.current?.focus()}
                    textContentType='oneTimeCode'
                    {...signup.getTextInputProps('password')} />
                <InputField placeholder="비밀번호 확인"
                    error={signup.errors.passwordConfirm}
                    touched={signup.touched.passwordConfirm}
                    secureTextEntry
                    ref={passwordConfirmRef}
                    onSubmitEditing={handleSubmit}
                    {...signup.getTextInputProps('passwordConfirm')} />
            </View>
            <CustomButton label="회원가입" onPress={handleSubmit} />
        </SafeAreaView>
    );
}

//const styles = StyleSheet.create({
const styling = (theme: ThemeMode) =>
    StyleSheet.create({
        container: {
            flex: 1,
            margin: 30,
        },
        inputContainer: {
            gap: 20,
            marginBottom: 30,
        },
    });

export default SignScreen;
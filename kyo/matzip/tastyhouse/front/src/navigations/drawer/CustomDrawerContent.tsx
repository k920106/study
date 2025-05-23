import React from 'react';
import { ActivityIndicator, Image, Platform, Pressable, SafeAreaView, StyleSheet, Text, View } from 'react-native';
import { DrawerContentComponentProps, DrawerContentScrollView, DrawerItemList } from '@react-navigation/drawer';
import useAuth from '@/hooks/queries/useAuth';
import { colors, mainNavigations, settingNavigations } from '@/constants';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import { ThemeMode } from '@/types';
import useThemeStore from '@/store/useThemeStore';

function CustomDrawerContent(props: DrawerContentComponentProps) {
    const { getProfileQuery } = useAuth();
    const { email, nickname, imageUri, kakaoImageUri } = getProfileQuery.data || {};
    const { theme } = useThemeStore();
    const styles = styling(theme);

    const handlePressSetting = () => {
        props.navigation.navigate(mainNavigations.SETTING, {
            screen: settingNavigations.SETTING_HOME,
        });
    };

    return (
        <SafeAreaView style={styles.container}>
            <DrawerContentScrollView
                {...props}
                scrollEnabled={false}
                contentContainerStyle={styles.contentContainer}>
                <View style={styles.userInfoContainer}>
                    <Pressable style={styles.userImageContainer}>
                        {imageUri === null && kakaoImageUri === null && (
                            <Image
                                source={require('@/assets/user-default.png')}
                                style={styles.userImage} />
                        )}
                        {imageUri === null && !!kakaoImageUri && (
                            <Image
                                source={{
                                    //uri: kakaoImageUri
                                    uri: `${Platform.OS === 'ios'
                                        ? 'http://localhost:3030/'
                                        : 'http://10.0.2.2:3030/'
                                        }${kakaoImageUri}`,
                                }}
                                style={styles.userImage} />
                        )}
                        {imageUri !== null && (
                            <Image
                                source={{
                                    //uri: imageUri
                                    uri: `${Platform.OS === 'ios'
                                        ? 'http://localhost:3030/'
                                        : 'http://10.0.2.2:3030/'
                                        }${imageUri}`,
                                }}
                                style={styles.userImage} />
                        )}
                    </Pressable>
                    <Text style={styles.nameText}>{nickname ?? email}</Text>
                </View>
                <DrawerItemList {...props} />
            </DrawerContentScrollView>
            <View style={styles.bottomContainer}>
                <Pressable style={styles.bottomMenu} onPress={handlePressSetting}>
                    {/* <MaterialIcons name={'settings'} color={colors.GRAY_700} size={18} /> */}
                    <MaterialIcons name={'settings'} color={colors[theme].GRAY_700} size={18} />
                    <Text style={styles.bottomMenuText}>설정</Text>
                </Pressable>
            </View>
        </SafeAreaView>
    );
}

//const styles = StyleSheet.create({
const styling = (theme: ThemeMode) =>
    StyleSheet.create({
        container: {
            flex: 1,
        },
        contentContainer: {
            //backgroundColor: colors.WHITE,
            backgroundColor: colors[theme].WHITE,
        },
        nameText: {
            //color: colors.BLACK,
            color: colors[theme].BLACK,
        },
        userInfoContainer: {
            alignItems: 'center',
            marginTop: 15,
            marginBottom: 30,
            marginHorizontal: 15,
        },
        userImageContainer: {
            width: 70,
            height: 70,
            borderRadius: 35,
            marginBottom: 10,
        },
        userImage: {
            width: '100%',
            height: '100%',
            borderRadius: 35,
        },
        bottomContainer: {
            flexDirection: 'row',
            justifyContent: 'flex-end',
            paddingHorizontal: 20,
            paddingVertical: 15,
            borderTopWidth: 1,
            //borderTopColor: colors.GRAY_200,
            borderTopColor: colors[theme].GRAY_200,
        },
        bottomMenu: {
            flexDirection: 'row',
            alignItems: 'center',
            gap: 5,
        },
        bottomMenuText: {
            fontWeight: '600',
            fontSize: 15,
            //color: colors.GRAY_700,
            color: colors[theme].GRAY_700,
        },
    });

export default CustomDrawerContent;
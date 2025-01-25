import { alerts } from '@/constants/messages';
import { useEffect } from 'react';
import { Alert, Linking, Platform } from 'react-native';
import { check, request, PERMISSIONS, Permission, RESULTS } from 'react-native-permissions';

type PermissionType = 'LOCATION' | 'PHOTO';

type PermissionOS = {
    [key in PermissionType]: Permission;
};

const androidPermissons: PermissionOS = {
    LOCATION: PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION,
    PHOTO: PERMISSIONS.ANDROID.READ_MEDIA_IMAGES,
};

const iosPermissons: PermissionOS = {
    LOCATION: PERMISSIONS.IOS.LOCATION_WHEN_IN_USE,
    PHOTO: PERMISSIONS.IOS.PHOTO_LIBRARY,
};

// function usePermission() {
function usePermission(type: PermissionType) {
    useEffect(() => {
        (async () => {
            const isAndroid = Platform.OS === 'android';
            // const permissionOS = isAndroid ? PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION : PERMISSIONS.IOS.LOCATION_WHEN_IN_USE;
            const permissionOS = isAndroid ? androidPermissons : iosPermissons;
            
            const checked = await check(permissionOS[type]);

            const showPermissonAlert = () => {
                Alert.alert(
                    // '위치 권한 허용이 필요합니다.',
                    // '설정 화면에서 위치 권한을 허용해 주세요.',
                    alerts[`${type}_PERMISSION`].TITLE,
                    alerts[`${type}_PERMISSION`].DESCRIPTION,
                    [
                        {
                            text: '설정하기',
                            onPress: () => Linking.openSettings(),
                        },
                        {
                            text: '취소',
                            style: 'cancel',
                        },
                    ],
                );
            };

            switch (checked) {
                case RESULTS.DENIED:
                    if(isAndroid) {
                        showPermissonAlert();
                        return;
                    }

                    // await request(permissionOS);
                    await request(permissionOS[type]);
                    break;
                case RESULTS.BLOCKED:
                case RESULTS.LIMITED:
                    showPermissonAlert();
                    break;
                default:
                    break;
            }
        })();
    }, []);
}

export default usePermission;
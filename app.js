import React from 'react';
import { Text, View, StyleSheet } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { SafeAreaProvider } from 'react-native-safe-area-context';
// Import MaterialCommunityIcons instead of Ionicons
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';

// --- Screen Components ---

// Home Screen Component
const HomeScreen = () => {
  return (
    <View style={styles.screenContainer}>
      <Text style={styles.screenText}>Home Page</Text>
    </View>
  );
};

// Search Screen Component
const SearchScreen = () => {
  return (
    <View style={styles.screenContainer}>
      <Text style={styles.screenText}>Search Page</Text>
    </View>
  );
};

// Profile Screen Component
const ProfileScreen = () => {
  return (
    <View style={styles.screenContainer}>
      <Text style={styles.screenText}>Profile Page</Text>
    </View>
  );
};

// --- Navigation Setup ---

const Tab = createBottomTabNavigator(); // Create a Bottom Tab Navigator

// Main App Component
export default function App() {
  // Ensure all required npm packages are installed:
  // @react-navigation/native, @react-navigation/bottom-tabs, react-native-screens, react-native-safe-area-context, react-native-vector-icons
  return (
    <SafeAreaProvider>
      <NavigationContainer>
        <Tab.Navigator
          screenOptions={({ route }) => ({
            tabBarIcon: ({ focused, color, size }) => {
              let iconName;

              // Use MaterialCommunityIcons names
              if (route.name === 'Home') {
                iconName = focused ? 'home' : 'home-outline'; // 'home' and 'home-outline' are common MaterialCommunityIcons
              } else if (route.name === 'Search') {
                iconName = focused ? 'magnify' : 'magnify'; // 'magnify' is a common search icon in MaterialCommunityIcons
              } else if (route.name === 'Profile') {
                iconName = focused ? 'account' : 'account-outline'; // 'account' and 'account-outline' for profile
              }

              // Return MaterialCommunityIcons component
              return <Icon name={iconName} size={size} color={color} />;
            },
            tabBarActiveTintColor: '#6200EE',
            tabBarInactiveTintColor: 'gray',
            tabBarStyle: {
              backgroundColor: '#FFFFFF',
              borderTopWidth: 0,
              elevation: 8,
              shadowColor: '#000',
              shadowOffset: { width: 0, height: -2 },
              shadowOpacity: 0.1,
              shadowRadius: 4,
              paddingBottom: 5,
              height: 60,
              paddingTop: 5,
            },
            tabBarLabelStyle: {
              fontSize: 12,
              fontWeight: '600',
            },
            headerShown: false,
          })}
        >
          <Tab.Screen name="Home" component={HomeScreen} />
          <Tab.Screen name="Search" component={SearchScreen} />
          <Tab.Screen name="Profile" component={ProfileScreen} />
        </Tab.Navigator>
      </NavigationContainer>
    </SafeAreaProvider>
  );
}

// --- Styles ---

const styles = StyleSheet.create({
  screenContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  screenText: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#333333',
  },
});
